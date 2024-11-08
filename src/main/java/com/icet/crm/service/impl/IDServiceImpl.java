package com.icet.crm.service.impl;

import com.icet.crm.repository.*;
import com.icet.crm.service.IDService;
import com.icet.crm.util.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class IDServiceImpl implements IDService {
    final AdminRepository adminRepository;
    final UserRepository userRepository;
    final VehicleRepository vehicleRepository;
    final BookingRepository bookingRepository;
    final EmailRepository emailRepository;
    final RepairRepository repairRepository;
    @Override
    public String generateID(ServiceType type) {
        switch (type){
            case USER:
                //userRepository.
        }
        return "";
    }
    private Integer generateID(){
        return new Random().nextInt(9999)+1;
    }
}
