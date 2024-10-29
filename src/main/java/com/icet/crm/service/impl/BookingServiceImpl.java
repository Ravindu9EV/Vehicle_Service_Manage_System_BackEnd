package com.icet.crm.service.impl;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.entity.Booking;
import com.icet.crm.repository.BookingRepository;
import com.icet.crm.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final ModelMapper mapper;
    @Override
    public void addBooiking(BookingDto bookingDto) {
        if(bookingDto!=null)repository.save(mapper.map(bookingDto,Booking.class));
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
    public List<BookingDto> findByServiceId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByServiceId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findByVehicleId(Integer id) {
        List<BookingDto> bookingDtos=new ArrayList<>();
        repository.findByVehicleId(id).forEach(booking -> bookingDtos.add(mapper.map(booking,BookingDto.class)));
        return bookingDtos;
    }
}
