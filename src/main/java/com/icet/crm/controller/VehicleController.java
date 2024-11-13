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

    @GetMapping("/find-by-made-year/{madeYear}")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> findByMadeYear(@PathVariable String madeYear){
        return service.findByMadeYear(madeYear);
    }
    @GetMapping("/find-by-model/{model}")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> findByModel(@PathVariable String model){
        return service.findByMadeYear(model);
    }
    @GetMapping("/find-by-model/{licensePlate}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto findByLicensePlate(@PathVariable String licensePlate){
        return service.findByLicensePlate(licensePlate);
    }
}
