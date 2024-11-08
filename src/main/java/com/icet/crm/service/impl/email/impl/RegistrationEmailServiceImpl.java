package com.icet.crm.service.impl.email.impl;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.Email;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.EmailRepository;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.impl.email.RegistrationEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service

@RequiredArgsConstructor
public class RegistrationEmailServiceImpl implements RegistrationEmailService {
    private static RegistrationEmailServiceImpl instance;



   final UserRepository userRepository;

   final VehicleRepository vehicleRepository;

   final EmailRepository emailRepository;


   final ModelMapper mapper;

   final JavaMailSender javaMailSender;

//    private RegistrationEmailServiceImpl(){
//
//    }
//
//    public static RegistrationEmailServiceImpl getInstance(){
//        return instance==null? instance=new RegistrationEmailServiceImpl() : instance;
//    }
//----------------send data to  sendEmail() , when user registered successfully----------------------------------


    @Override
    public EmailDto sendRegistrationEmail(User user,Vehicle vehicle) {
        try{
            if(user!=null & vehicle!=null){
                String body=setUserRegistrationBody(user,vehicle);
                if(body.isEmpty()){
                    return null;
                }else{
                    EmailDto emailDto=sendRegistrationEmail("yravindu18@gmail.com",user.getEmail(),"Registration",body);
                    if(emailDto!=null){
                        if(sendEmail(emailDto)!=null){
                            emailRepository.save(mapper.map(emailDto, Email.class));
                            sendEmail(sendEmail(emailDto));
                            return emailDto;
                        }
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }

    //----------------wrap details into EmailDto-----------------
    private EmailDto sendRegistrationEmail(String sender,String recipient,String subject,String body){


        return new EmailDto(sender,recipient,subject,body,formatDateToString(new Date()));
    }

    //------------------------format Email Body------------------
    private String setUserRegistrationBody(User user,Vehicle vehicle){
        if(user!=null & vehicle!=null){
            return String.format("Hello %-10s,\n\t\tWelcome to VSM!.You joined with the Best vehicle Repair Shop in Sri Lanka...\n\nYour Registration Details:\n\n\n\t\tUser Id: %-20d\n\n\tVehicle Id: %-20d\n\n\t*Important : Enter Your Vehicle Id when you booking a service...\n\n\n\tThank you for Joining with us...\nBest Regards , VSM Team.",user.getName(),user.getId(),vehicle.getId());
        }
        return "";
    }
    //-------set Email--------------------
    private SimpleMailMessage sendEmail(EmailDto emailDto){
        //String sender,String recipient,String subject,String body

        if(!(emailDto.getSender().isEmpty()) & !(emailDto.getRecipient().isEmpty()) & !(emailDto.getSubject().isEmpty()) & !(emailDto.getBody().isEmpty()) & !(emailDto.getDate().isEmpty())){
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(emailDto.getSender());
            message.setTo(emailDto.getRecipient());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getBody());
            message.setSentDate(formatDate(emailDto.getDate()));
            return message;

        }
        return null;
    }
    //--------------send Email--------------
    private boolean sendEmail(SimpleMailMessage message){
        if(message!=null){
            System.out.println(message);
            javaMailSender.send(message);
            return true;
        }
        return false;
    }

    //---------------format date--------------
    private Date formatDate(String date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //-----------Format Date to String---------
    public String formatDateToString(Date date){

        return new SimpleDateFormat("MM-dd-yyyy").format(new Date());
    }
}

