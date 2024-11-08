package com.icet.crm.service;

import com.icet.crm.dto.BookingDto;

import java.util.List;

public interface BookingService {
    boolean addBooiking(BookingDto bookingDto);
    BookingDto findBooking(Integer id);
    List<BookingDto> getAll();
    List<BookingDto> findByDate(String date);
    List<BookingDto> findByRepairId(Integer id);
    List<BookingDto> findByVehicleId(Integer id);
    void updateBooking(BookingDto bookingDto);
    BookingDto getAvailbleBooking(String bookedDate,String bookedTime);
}
