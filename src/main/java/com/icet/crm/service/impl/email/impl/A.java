package com.icet.crm.service.impl.email.impl;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.service.BookingService;

import java.util.List;

public class A implements BookingService {
    @Override
    public boolean addBooiking(BookingDto bookingDto) {
        return false;
    }

    @Override
    public BookingDto findBooking(Integer id) {
        return null;
    }

    @Override
    public List<BookingDto> getAll() {
        return List.of();
    }

    @Override
    public List<BookingDto> findByDate(String date) {
        return List.of();
    }

    @Override
    public List<BookingDto> findByRepairId(Integer id) {
        return List.of();
    }

    @Override
    public List<BookingDto> findByVehicleId(Integer id) {
        return List.of();
    }

    @Override
    public boolean updateBooking(BookingDto bookingDto) {
        return false;
    }

    @Override
    public BookingDto getAvailbleBooking(String bookedDate, String bookedTime) {
        return null;
    }

    @Override
    public boolean deleteBooking(Integer id) {
        return false;
    }

    @Override
    public List<BookingDto> sortByDate() {
        return List.of();
    }
}
