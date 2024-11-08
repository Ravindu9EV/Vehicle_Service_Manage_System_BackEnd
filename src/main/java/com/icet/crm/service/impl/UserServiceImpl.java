package com.icet.crm.service.impl;

import com.icet.crm.dto.EmailDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.Email;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.EmailRepository;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.SendEmailService;
import com.icet.crm.service.UserService;
import com.icet.crm.service.VehicleService;
import com.icet.crm.service.impl.email.RegistrationEmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final VehicleRepository vehicleRepository;
    private final ModelMapper mapper;
    final PasswordEncoder passwordEncoder;
    final PlatformTransactionManager platformTransactionManager;
    final VehicleService vehicleService;
    final EmailRepository emailRepository;
    final RegistrationEmailService registrationEmailService;

    @Override

    public boolean addUser(UserDto userDto) {
        if (userDto == null | userDto.getVehicleEntities().isEmpty()) {
            return false;
        }
        DefaultTransactionDefinition deft = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(deft);
        User newUser = null;
        boolean isUserSaved = false;
        boolean isVehicleSaved = false;
        boolean isDataSaved = false;
        System.out.println(userDto.getEmail());
        User user = mapper.map(userDto, User.class);
        Vehicle newVehicle = null;
        if (repository.findByEmail(userDto.getEmail()) != null) {
            System.out.println("User Already in");
            return false;
        }
        for (Vehicle v : user.getVehicleEntities()) {
            if (vehicleRepository.findByLicensePlate(v.getLicensePlate()) == null) {
                newVehicle = v;
                break;
            }
        }
        try {


            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newUser = mapper.map(userDto, User.class);
            System.out.println(newUser);
            newUser = repository.save(newUser);

            System.out.println(newUser);
            isUserSaved = true;
            //update userId of the vehicleEntity in userEntity and then save in DB;
            newVehicle.setUserId(newUser.getId());
            System.out.println(newVehicle);
            if (isUserSaved) {
                newVehicle = vehicleRepository.save(newVehicle);
                List<Vehicle> vehicles = new ArrayList<>();

                vehicles.add(newVehicle);
                newUser.setVehicleEntities(vehicles);
                System.out.println(newVehicle);
                isVehicleSaved = true;

            }

            if (isUserSaved & isVehicleSaved) {
                System.out.println(newUser + "-" + newVehicle);
                platformTransactionManager.commit(status);
                isDataSaved = true;

                System.out.println("saved");

            }

            if (isDataSaved) {
                EmailDto emailDto=registrationEmailService.sendRegistrationEmail(newUser, newVehicle);
                if (emailRepository.save(mapper.map(emailDto, Email.class))!=null){

                    System.out.println("email sent" +
                            emailDto);
                    return true;
                }


            }

        } catch (Exception e) {
            System.out.println(e);
            return false;


        }

        return false;

    }

    @Override
    public boolean deleteUser(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public UserDto findById(Integer id) {
        User user = repository.getReferenceById(id);
        return user != null ? mapper.map(user, UserDto.class) : null;
    }

    @Override
    public UserDto findByEmail(String email) {
        return !email.isEmpty() ? mapper.map(repository.findByEmail(email), UserDto.class) : null;
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<UserDto> users = new ArrayList<>();
//        getAll().forEach(userDto -> {
//            if (userDto.getName().equals(name)){
//                users.add(userDto);
//            }
//        });
        for (User user : repository.findByName(name)) {
            users.add(mapper.map(user, UserDto.class));
        }
        return users;

    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        repository.findAll().forEach(user -> users.add(mapper.map(user, UserDto.class)));
        return users;
    }

    @Override
    public void updateUser(UserDto user) {
        repository.save(mapper.map(user, User.class));
    }
}
