package com.icet.crm.service.impl.email.impl;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.entity.Email;
import com.icet.crm.entity.User;
import com.icet.crm.repository.EmailRepository;
import com.icet.crm.service.impl.email.OTPEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPEmailServiceImpl implements OTPEmailService {
    private final JavaMailSender javaMailSender;
    final EmailRepository emailRepository;
    final ModelMapper mapper;
    @Override
    public EmailDto sendOTP(User user,Integer otp) {
       try{
           String body= setOTPEmailBody(user,otp);
           String crntDateTime= LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
           return sendOTPEmail(user.getEmail(), "Password Reset",body,crntDateTime);
       }catch (Exception e){
           log.info(e.toString());
           return null;
       }

    }



    //------------get EmailDto -------------
    private EmailDto sendOTPEmail(String recipient,String subject,String body,String dateTime){
        EmailDto emailDto=null;
        if(recipient.isEmpty() && subject.isEmpty() && dateTime.isEmpty()){
            return emailDto;
        }else {

            emailDto= new EmailDto("yravindu18@gmail.com",recipient,subject,body,dateTime);
            if(emailDto!=null){
                emailRepository.save(mapper.map(emailDto, Email.class));
                sendEmail(emailDto);
                return emailDto;
            }
        }

        return emailDto;
    }

    //------------------------format Email Body------------------
    private String setOTPEmailBody(User user, Integer otp){

        if(user!=null ){
            return String.format("Dear %-10s,\n\t\tPlease Use  %-10d as OTP code .\nBest Regards , VSM Team.",user.getName(),otp);

        }
        return "";
    }
    //-------set Email--------------------
    private boolean sendEmail(EmailDto emailDto){

        if(!(emailDto.getSender()).isEmpty()&& !(emailDto.getRecipient()).isEmpty() && !(emailDto.getSubject()).isEmpty() ){
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(emailDto.getSender());
            message.setTo(emailDto.getRecipient());
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getBody());
            message.setSentDate(formatDate(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)));

            return sendEmail(message);

        }
        return false;
    }
    //---------------format date--------------
    private Date formatDate(String date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");

        try {
            String d=date.substring(4,6)+"-"+date.substring(6,8)+"-"+date.substring(0,4);
            System.out.println(d);
            return dateFormat.parse(d);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    //--------------send Email--------------
    private boolean sendEmail(SimpleMailMessage message){
        if(message!=null){

            javaMailSender.send(message);
            System.out.println(message);
            return true;
        }
        return false;
    }
}
