package com.icet.crm.controller;

import com.icet.crm.dto.RepairDto;
import com.icet.crm.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/repair")
@RequiredArgsConstructor
public class RepairController {
    private final RepairService service;
    @PostMapping("/add-repair")
    @ResponseStatus(HttpStatus.OK)
    public boolean addService(@RequestBody RepairDto repairDto){
       return service.addRepair(repairDto);
    }

    @DeleteMapping("/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteRepair(@PathVariable  Integer id){
        return service.deleteRepair(id);

    }

    @GetMapping("/search-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RepairDto searchRepairById(@PathVariable Integer id){
        return service.findReapairById(id);
    }
    @GetMapping("/search-by-type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<RepairDto> searchRepairByType(@PathVariable String type){
        return service.findRepairByType(type);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<RepairDto> findAll(){
        return service.getAll();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateRepair(@RequestBody RepairDto repairDto){
        service.updateRepair(repairDto);
    }
}
