package com.icet.crm.repository;

import com.icet.crm.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    List<Vehicle> findByModel(String model);
    List<Vehicle> findByMadeYear(String year);
    Vehicle findByLicensePlate(String licensePlate);
    Vehicle findByUserIdAndLicensePlate(Integer userId,String licensePlate);
}
