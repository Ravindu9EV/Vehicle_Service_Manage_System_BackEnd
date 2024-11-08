package com.icet.crm.service;

import com.icet.crm.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    boolean addVehicle(VehicleDto vehicleDto);
    boolean deleteById(Integer id);
    List<VehicleDto> getAll();
    List<VehicleDto> findByModel(String model);
    List<VehicleDto> findByMadeYear(String year);
    VehicleDto findByLicensePlate(String licensePlate);
    VehicleDto findById(Integer id);
    void updateVehicle(VehicleDto vehicle);
}
