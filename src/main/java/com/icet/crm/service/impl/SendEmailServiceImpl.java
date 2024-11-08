package com.icet.crm.service.impl;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.EmailRepository;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.SendEmailService;
import com.icet.crm.service.impl.email.RepairBookingEmailService;
import com.icet.crm.service.impl.email.RegistrationEmailService;
import com.icet.crm.util.EmailServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    final ModelMapper mapper;
    final EmailRepository repository;
    final UserRepository userRepository;
    final RegistrationEmailService registrationEmailService;
    final RepairBookingEmailService repairBookingEmailService;
    final VehicleRepository vehicleRepository;

    public boolean sendUserRegistrationEmail(UserDto userDto) {
        try {
            if(userDto!=null & !userDto.getVehicleEntities().isEmpty()){
                User user=mapper.map(userDto, User.class);
                System.out.println(user);
                for(VehicleDto dto:userDto.getVehicleEntities()){

                    if(vehicleRepository.findByLicensePlate(dto.getLicensePlate())==null){
                        return registrationEmailService.sendRegistrationEmail(user,mapper.map(dto, Vehicle.class))!=null ;
                    }
                }


            }
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
       return false;
    }

    @Override
    public boolean sendRepairBookingEmail(BookingDto bookingDto) {
        if(bookingDto!=null){
            Booking booking=mapper.map(bookingDto, Booking.class);

           return repairBookingEmailService.sendRepairBookingEmail(booking)!=null;
        }
        return false;
    }


    public boolean sendUserRegistrationEmail(User user) {
        return false;
    }

    @Override
    public boolean sendRepairBookingEmail(Booking booking) {
        return false;
    }


}
