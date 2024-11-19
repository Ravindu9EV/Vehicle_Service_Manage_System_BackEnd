package com.icet.crm.service.impl;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.EmailDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.repository.BookingRepository;
import com.icet.crm.service.BookingService;
import com.icet.crm.service.impl.email.RepairBookingEmailService;
import com.icet.crm.util.EmailServiceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final ModelMapper mapper;
    final RepairBookingEmailService repairBookingEmailService;
    @Override
    public boolean addBooiking(BookingDto bookingDto) {
        boolean isAvailable=false;
        if(bookingDto!=null){
            if(getAvailbleBooking(bookingDto.getBookedDate(), bookingDto.getBookedTime())==null){
                Booking booking=mapper.map(bookingDto, Booking.class);

                if(getAvailbleBooking(booking.getBookedDate(), booking.getBookedTime())==null){
                    repository.save(booking);
                    isAvailable=true;
                    if(isAvailable){
                        EmailDto emailDto=repairBookingEmailService.sendRepairBookingEmail(booking);
                        System.out.println(emailDto);
                    }
                }else{
                    return false;
                }

            }

        }
        return isAvailable;

    }



    @Override
    public BookingDto findBooking(Integer id) {
        return mapper.map(repository.findById(id),BookingDto.class);
    }

    @Override
    public List<BookingDto> getAll() {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findAll().forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByDate(String date) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByBookedDate(date).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByRepairId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByRepairId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByVehicleId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByVehicleId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public boolean updateBooking(BookingDto bookingDto) {

        if(bookingDto.getId()!=null && bookingDto.getVehicleId()!=null && bookingDto.getBookedDate()!=null && bookingDto.getBookedTime()!=null && bookingDto.getRepairId()!=null && bookingDto.getDescription()!=null){
            try{
                Booking booking=new Booking(bookingDto.getId(), bookingDto.getVehicleId(), bookingDto.getBookedDate(), bookingDto.getBookedTime(), bookingDto.getRepairId(), bookingDto.getDescription());
                System.out.println(booking);
                repository.save(booking);
                repairBookingEmailService.sendRepairBookingEmail(booking);
                return true;
            }catch (Exception e){
                log.info(e.toString());
                return false;
            }

        }
        else {
            return false;
        }
    }

    @Override
    public BookingDto getAvailbleBooking(String bookedDate, String bookedTime) {
       Booking booking=repository.findByBookedDateAndBookedTime(bookedDate,bookedTime);
        return booking==null?null:mapper.map(booking,BookingDto.class);
    }

    @Override
    public boolean deleteBooking(Integer id) {
        repository.deleteById(id);
        return true;
    }

}
