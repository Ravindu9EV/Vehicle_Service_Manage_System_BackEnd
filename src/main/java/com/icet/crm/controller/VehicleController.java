package com.icet.crm.controller;

import com.icet.crm.dto.VehicleDto;
import com.icet.crm.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public boolean addVehicle(@RequestBody VehicleDto vehicle){
        System.out.println(vehicle);
        return service.addVehicle(vehicle);
    }

    @GetMapping("/search-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto searchVehicleById(@PathVariable Integer id){
        VehicleDto vehicleDto=service.findById(id);
        System.out.println(vehicleDto);
        return vehicleDto;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateVehicle(@RequestBody VehicleDto vehicleDto){
        service.updateVehicle(vehicleDto);
    }

    @DeleteMapping("/delete-by-id/filter")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVehicle(@RequestParam(required = true) Integer id){
        service.deleteById(id);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/find-by-made-year/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> findByMadeYear(@RequestParam String madeYear){
        return service.findByMadeYear(madeYear);
    }
    @GetMapping("/find-by-model/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> findByModel(@RequestParam String model){
        return service.findByMadeYear(model);
    }
    @GetMapping("/find-by-license-plate/filter")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto findByLicensePlate(@RequestParam String licensePlate){
        return service.findByLicensePlate(licensePlate);
    }
    @GetMapping("/find-by-userId/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> findByLicensePlate(@RequestParam(required = true) Integer userId){
        return service.findByUserid(userId);
    }
}
