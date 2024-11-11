package com.icet.crm.controller;

import com.icet.crm.dto.BookingDto;
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

    @PostMapping("/add-booking")
    @ResponseStatus(HttpStatus.OK)
    public boolean addBooking(@RequestBody BookingDto bookingDto){
        System.out.println(bookingDto);
        return service.addBooiking(bookingDto);
    }

    @GetMapping("/search-booking-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingDto searchBookingById(@PathVariable Integer id){
        return service.findBooking(id);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(@RequestBody BookingDto bookingDto){
        service.updateBooking(bookingDto);
    }


    @GetMapping("/search-booking-by-date/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> searchBookingByDate(@RequestParam(required = true) String bookedDate){
        return service.findByDate(bookedDate);
    }

    @GetMapping("/search-by-vehicle-id/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> searchBookingByVehicleId(@PathVariable Integer vehicleId){
        return service.findByVehicleId(vehicleId);
    }

    @GetMapping("/search-by-repair-id/?")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> searchBookingByRepairId(@RequestParam(required = true) Integer repairId){
        return service.findByRepairId(repairId);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/get-available-booking/filter")
    @ResponseStatus(HttpStatus.OK)
    public BookingDto checkBookingIsAvailable(@RequestParam(required = true) String bookedDate,@RequestParam String bookedTime){
        return service.getAvailbleBooking(bookedDate,bookedTime);
    }

}
