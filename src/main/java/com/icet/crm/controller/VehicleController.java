package com.icet.crm.controller;

import com.icet.crm.dto.VehicleDto;
import com.icet.crm.service.VehicleService;
import jakarta.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void addVehicle(@RequestBody VehicleDto vehicle){
        service.addVehicle(vehicle);
    }

    @GetMapping("/search-by-id")
    @ResponseStatus(HttpStatus.FOUND)
    public VehicleDto searchVehicleById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateVehicle(@RequestBody VehicleDto vehicleDto){
        service.updateVehicle(vehicleDto);
    }

    @DeleteMapping("/delete-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicle(Integer id){
        service.deleteById(id);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<VehicleDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/find-by-made-year/{madeYear}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<VehicleDto> findByMadeYear(@PathVariable String madeYear){
        return service.findByMadeYear(madeYear);
    }
    @GetMapping("/find-by-model/{model}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<VehicleDto> findByModel(@PathVariable String model){
        return service.findByMadeYear(model);
    }
    @GetMapping("/find-by-model/{licensePlate}")
    @ResponseStatus(HttpStatus.FOUND)
    public VehicleDto findByLicensePlate(@PathVariable String licensePlate){
        return service.findByLicensePlate(licensePlate);
    }
}
