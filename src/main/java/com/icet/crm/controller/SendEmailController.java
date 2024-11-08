package com.icet.crm.controller;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.EmailDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class SendEmailController {
    final SendEmailService sendEmailService;

//    @PostMapping("/sendRegistrationEmail")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public boolean sendEmail(@RequestBody UserDto userDto){
//       return sendEmailService.sendUserRegistrationEmail(userDto);
//    }

    @PostMapping("/sendRepairBookingEmail")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean sendEmail(@RequestBody BookingDto bookingDto){
        return sendEmailService.sendRepairBookingEmail(bookingDto);
    }
}
