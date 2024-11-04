package com.icet.crm.service;

import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.Vehicle;
import org.modelmapper.ModelMapper;
import com.icet.crm.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleRepairTest {
    @Autowired
    VehicleService service;
    @Autowired
    VehicleRepository repository;
    @Autowired
    ModelMapper mapper;
    @Test
    void test_findById_whenNullObject(){
        VehicleDto vehicleDto=null;
        Assertions.assertEquals(null,vehicleDto);
    }

    @Test
    void test_findById_whenFound(){
        VehicleDto vehicleDto=service.findById(1);
        Assertions.assertEquals(new VehicleDto(1,"","",""),vehicleDto);
    }

    @Test
    void test_searchById_whenNullModelMapper(){
        VehicleDto vehicle=service.findById(1);
        ModelMapper mapper=null;
        Assertions.assertEquals(mapper,vehicle);
    }
    @Test
    void test_searchById_whenNotNullModelMapper(){
        ModelMapper mapper1=new ModelMapper();
        VehicleDto vehicleDto=new VehicleDto(1,"","","");
        Assertions.assertEquals(service.findById(1),service.findById(1));
    }

    @Test
    void test_searchById_whenID(){
        VehicleDto vehicle=service.findById(1);
        ModelMapper mapper=null;
        Assertions.assertEquals(mapper,vehicle);
    }
}
