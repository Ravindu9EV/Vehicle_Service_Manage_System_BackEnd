package com.icet.crm.service;

import com.icet.crm.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void addBooiking(BookingDto bookingDto);
    BookingDto findBooking(Integer id);
    List<BookingDto> getAll();
    List<BookingDto> findByDate(String date);
    List<BookingDto> findByServiceId(Integer id);
    List<BookingDto> findByVehicleId(Integer id);
}
