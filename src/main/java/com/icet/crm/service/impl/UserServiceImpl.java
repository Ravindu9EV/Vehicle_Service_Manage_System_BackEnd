package com.icet.crm.service.impl;

import com.icet.crm.Main;
import com.icet.crm.dto.AdminDto;
import com.icet.crm.dto.EmailDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.dto.VehicleDto;
import com.icet.crm.entity.Admin;
import com.icet.crm.entity.Email;
import com.icet.crm.entity.User;
import com.icet.crm.entity.Vehicle;
import com.icet.crm.repository.EmailRepository;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.repository.VehicleRepository;
import com.icet.crm.service.SendEmailService;
import com.icet.crm.service.UserService;
import com.icet.crm.service.VehicleService;
import com.icet.crm.service.impl.email.OTPEmailService;
import com.icet.crm.service.impl.email.RegistrationEmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.internal.Collections;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
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
    final OTPEmailService otpEmailService;
    @Override

    public boolean addUser(UserDto userDto) {
//        if (userDto.getName().isEmpty() || userDto.getEmail().isEmpty() || userDto.getPassword().isEmpty() ||  userDto.getVehicleEntities()==null) {
//            System.out.println("empty filed");
//            return false;
//        }
        //else if(userDto.getPassword()==null){
//            return false;
//        }else if(userDto.getEmail()==null){
//            return false;
//        }
//        else if(userDto.getName()==null){
//            return false;
//        }else if(userDto.getContact()==null){
//            return false;
//        }

        DefaultTransactionDefinition deft = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(deft);
        User newUser = null;
        boolean isUserSaved = false;
        boolean isVehicleSaved = false;
        boolean isDataSaved = false;
        System.out.println(userDto.getEmail());
        User user = mapper.map(userDto, User.class);
        Vehicle newVehicle = null;

        if (repository.findByEmail(userDto.getEmail()) != null) {  //Check A User exist with same Email
            System.out.println("User Already in");
            return false;
        }

        try {
            for (Vehicle v : user.getVehicleEntities()) {
                if (vehicleRepository.findByLicensePlate(v.getLicensePlate()) == null) {  //Check A vehicle exist with given License Plate
                    newVehicle = v;
                    break;
                }else{
                    return false;
                }
            }

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
        User user=null;
        UserDto userDto=null;
        if(email==null){
            return null;
        }
        try{
            user=repository.findByEmail(email);
            if(user!=null){
                userDto=mapper.map(user, UserDto.class);
                System.out.println(userDto);
                return userDto;
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println(userDto);
            return userDto;
            //return !email.isEmpty() ? userDto: null;
        }
        System.out.println(userDto);
        return userDto;
    }

    @Override
    public UserDto findByEmailAndPassword(String email, String password) {

        UserDto userDto=findByEmail(email);
        if(userDto!=null & passwordEncoder.matches(password, userDto.getPassword())){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userDto;
        }
        return userDto;
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<UserDto> users = new ArrayList<>();
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
    public boolean updateUser(UserDto userDto) {

        DefaultTransactionDefinition deft = new DefaultTransactionDefinition();
        TransactionStatus status = platformTransactionManager.getTransaction(deft);

        boolean isUpdate=false;

        LinkedList<Vehicle> vehicleLinkedList=new LinkedList<>();
        List<VehicleDto>  vehicleDtos=userDto.getVehicleEntities();
        try {

            for(Vehicle vehicle1:vehicleRepository.findByUserId(userDto.getId())){
                for(VehicleDto vDto:vehicleDtos){
                    if(vehicle1.getId().equals(vDto.getId())){
                        vehicleLinkedList.remove(vehicle1); //remove the vehicle object with old details
                        vehicleLinkedList.push(mapper.map(vDto,Vehicle.class));//add updated vehicle object
                    }

                }
            }

            vehicleRepository.saveAll(vehicleLinkedList);
            User updatedUser=mapper.map(userDto, User.class);
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            System.out.println(updatedUser);
            repository.save(updatedUser);
            platformTransactionManager.commit(status);
            isUpdate=true;

            //userVehicles=null;
        }catch (Exception e){
            isUpdate=false;
            System.out.println(e);
        }

        return isUpdate;

    }

    @Override
    public Integer resetPassword(String email){
        try{
            System.out.println(email);
            User user=mapper.map(findByEmail(email),User.class);
            Integer otp=generateOTP();
            return otpEmailService.sendOTP(user,otp)!=null ? otp : -1;
        }catch (Exception e){
            log.info(e.toString());
            return -1;
        }
    }
    private Integer generateOTP(){
        Integer otp=new Random().nextInt(999999);
        System.out.println(otp);
        return otp;
    }
}
