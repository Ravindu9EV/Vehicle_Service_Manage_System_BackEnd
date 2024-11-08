package com.icet.crm.service.impl.email.impl;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.*;
import com.icet.crm.repository.*;
import com.icet.crm.service.impl.email.RepairBookingEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RepairBookingEmailServiceImpl implements RepairBookingEmailService {
   final BookingRepository bookingRepository;

    final UserRepository userRepository;

    final VehicleRepository vehicleRepository;

    final RepairRepository repairRepository;

    final JavaMailSender javaMailSender;

    final EmailRepository emailRepository;

    final ModelMapper mapper;



    @Override
    public EmailDto sendRepairBookingEmail(Booking booking) {

        if(booking!=null){
            Vehicle vehicle= vehicleRepository.findById(booking.getVehicleId()).get();
            if(vehicle!=null){
                booking.getVehicleId();
                User user=userRepository.findById(vehicle.getUserId()).get();
                System.out.println(user);
                String body=setBookingEmailBody(user,booking);
                if(user!=null){
                    Repair repair=repairRepository.findById(booking.getRepairId()).get();

                    if(repair!=null){
                       return sendBookingEmail(user.getEmail(),repair.getType(),body,booking.getBookedDate());
                    }

                }
            }
        }
        return null;
    }

    //------------get EmailDto -------------
    private EmailDto sendBookingEmail(String recipient,String subject,String body,String bookingDate){
        EmailDto emailDto=null;
        if(recipient.isEmpty() && subject.isEmpty() && bookingDate.isEmpty()){
            return emailDto;
        }else {

            emailDto= new EmailDto("yravindu18@gmail.com",recipient,subject,body,bookingDate);
            if(emailDto!=null){
                emailRepository.save(mapper.map(emailDto, Email.class));
                sendEmail(emailDto);
                return emailDto;
            }
        }

        return emailDto;
    }

    //------------------------format Email Body------------------
    private String setBookingEmailBody(User user,Booking booking){

        if(user!=null & booking!=null){
            return String.format("Dear %-10s,\n\t\tSuccessfully Booked Your repair.Please Make Sure to arrive on %-10s.\n\n\n\tBooking ID: %-20d\n\n\tVehicle Id: %-20s\n\n\tYour Repair Date: %-20s\n\n\tRepair Time: %-20s\n\n\tRepair Id: %-20d\n\n\n\tWe are waiting to Provide our Service.Thank you for choosed\n\tour Service.\nBest Regards , VSM Team.",user.getName(),booking.getBookedTime(),booking.getId(),booking.getVehicleId(),booking.getBookedDate(),booking.getBookedTime(),booking.getRepairId());

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
            message.setSentDate(formatDate(emailDto.getDate()));
            return sendEmail(message);

        }
        return false;
    }
    //---------------format date--------------
    private Date formatDate(String date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
        try {
            return dateFormat.parse(date);
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
