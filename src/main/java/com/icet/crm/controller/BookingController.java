package com.icet.crm.controller;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.dto.VehicleDto;
import com.icet.crm.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @PostMapping("/add-Booking")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBooking(@RequestBody BookingDto bookingDto){
        service.addBooiking(bookingDto);
    }

    @GetMapping("/search-booking-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingDto searchBookingById(@PathVariable Integer id){
        return service.findBooking(id);
    }

    @GetMapping("/search-booking-by-date/{date}")
    public List<BookingDto> searchBookingByDate(@PathVariable String date){
        return service.findByDate(date);
    }

    @GetMapping("/search-booking-by-vehicle-id/{vehicleId}")
    public List<BookingDto> searchBookingByVehicleId(@PathVariable Integer vehicleId){
        return service.findByVehicleId(vehicleId);
    }

    @GetMapping("/search-vehicle-by-service-id/{serviceId}")
    public List<BookingDto> searchBookingByServiceId(Integer serviceId){
        return service.findByServiceId(serviceId);
    }



}
