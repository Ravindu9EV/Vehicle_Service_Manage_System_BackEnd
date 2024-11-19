package com.icet.crm.service.impl;

import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository repository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    final PlatformTransactionManager platformTransactionManager;
    @Override
    public boolean addVehicle(VehicleDto vehicleDto) {
        if(vehicleDto!=null){
            User user=userRepository.findById(vehicleDto.getUserId()).get();

            System.out.println(user);
            DefaultTransactionDefinition definition=new DefaultTransactionDefinition();
            TransactionStatus status=platformTransactionManager.getTransaction(definition);
            try{
                Vehicle v=repository.save(mapper.map(vehicleDto,Vehicle.class));
                user.getVehicleEntities().add(v);
                User u=userRepository.save(user);

                if(v!=null & u!=null){

                    platformTransactionManager.commit(status);
                    System.out.println(v+"\n"+u);

                    return true;
                }
            }catch (Exception e){
                log.info(e.toString());

                return false;
            }
            //return true;
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
        try {
            Vehicle vehicle =repository.getReferenceById(id);
            try{

                return mapper.map(vehicle,VehicleDto.class);
            }catch (EntityNotFoundException e){
                System.out.println(e);
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public void updateVehicle(VehicleDto vehicle) {
        repository.save(mapper.map(vehicle,Vehicle.class));
    }

    @Override
    public List<VehicleDto> findByUserid(Integer userId) {
        List<VehicleDto> vehicleDtos=new ArrayList<>();
        try{
            for(Vehicle entity:  repository.findByUserId(userId)){
                vehicleDtos.add(mapper.map(entity,VehicleDto.class));
            }
        }catch (Exception e){
            System.out.println(e);
            return vehicleDtos;
        }
        return vehicleDtos;
    }
}
