package com.icet.crm.service;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;

public interface SendEmailService {
    //boolean sendUserRegistrationEmail(UserDto userDto);
    boolean sendRepairBookingEmail(BookingDto bookingDto);

    //boolean sendUserRegistrationEmail(User user);
    boolean sendRepairBookingEmail(Booking booking);
}
