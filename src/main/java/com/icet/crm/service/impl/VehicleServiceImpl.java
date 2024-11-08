package com.icet.crm.service.impl;

import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository repository;
    private final ModelMapper mapper;
    @Override
    public boolean addVehicle(VehicleDto vehicleDto) {
        if(vehicleDto!=null){
            repository.save(mapper.map(vehicleDto,Vehicle.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }


    @Override
    public List<VehicleDto> getAll() {
        List<VehicleDto> vehicleDtos=new ArrayList<>();
        repository.findAll().forEach(vehicle -> vehicleDtos.add(mapper.map(vehicle,VehicleDto.class)));
        return vehicleDtos;
    }

    @Override
    public List<VehicleDto> findByModel(String model) {
        List<VehicleDto> vehicleDtos=new ArrayList<>();
        repository.findByModel(model).forEach(vehicle -> vehicleDtos.add(mapper.map(vehicle,VehicleDto.class)));
        return vehicleDtos;
    }

    @Override
    public List<VehicleDto> findByMadeYear(String year) {
        List<VehicleDto> vehicleDtos=new ArrayList<>();
        repository.findByMadeYear(year).forEach(vehicle -> vehicleDtos.add(mapper.map(vehicle,VehicleDto.class)));
        return vehicleDtos;
    }

    @Override
    public VehicleDto findByLicensePlate(String licensePlate) {
        Vehicle vehicle =repository.findByLicensePlate(licensePlate);
        return vehicle !=null ? mapper.map(vehicle,VehicleDto.class) : null;
    }

    @Override
    public VehicleDto findById(Integer id) {
        Vehicle vehicle =repository.getReferenceById(id);
         return vehicle!=null ? mapper.map(vehicle,VehicleDto.class) : null;
    }

    @Override
    public void updateVehicle(VehicleDto vehicle) {
        repository.save(mapper.map(vehicle,Vehicle.class));
    }
}
