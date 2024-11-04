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
    @ResponseStatus(HttpStatus.FOUND)
    public BookingDto searchBookingById(@PathVariable Integer id){
        return service.findBooking(id);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBooking(@RequestBody BookingDto bookingDto){
        service.updateBooking(bookingDto);
    }


    @GetMapping("/search-booking-by-date/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<BookingDto> searchBookingByDate(@PathVariable String date){
        return service.findByDate(date);
    }

    @GetMapping("/search-by-vehicle-id/{vehicleId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<BookingDto> searchBookingByVehicleId(@PathVariable Integer vehicleId){
        return service.findByVehicleId(vehicleId);
    }

    @GetMapping("/search-by-repair-id/{repairId}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<BookingDto> searchBookingByRepairId(Integer repairId){
        return service.findByRepairId(repairId);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<BookingDto> getAll(){
        return service.getAll();
    }

}
