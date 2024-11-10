package com.icet.crm.service.impl;

import com.icet.crm.dto.AdminDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.entity.Admin;
import com.icet.crm.entity.User;
import com.icet.crm.repository.AdminRepository;
import com.icet.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

        private final AdminRepository repository;
        private final ModelMapper mapper;
        private final PasswordEncoder passwordEncoder;
    @Override
    public void addAdmin(AdminDto adminDto) {
       if(adminDto!=null) {
           adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
           repository.save(mapper.map(adminDto, Admin.class));
       }
    }

    @Override
    public AdminDto findById(Integer id) {
        Admin admin=repository.findById(id).get();
        return admin==null? null : mapper.map(admin, AdminDto.class);
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<AdminDto> getAll() {
        List<AdminDto> admins=new ArrayList<>();
        repository.findAll().forEach(admin -> {

            admins.add(mapper.map(admin, AdminDto.class));

        });
        return admins;
    }

    @Override
    public void updateAdmin(AdminDto adminDto) {
        repository.save(mapper.map(adminDto,Admin.class));
    }

    @Override
    public AdminDto findByEmail(String email) {
//        Admin admin=null;
//       if(email.equals(null)){
//           return null;
//       }
//           try{
//               admin=repository.findByEmail(email);
//               if(admin!=null){
//                   System.out.println(admin);
//                   return mapper.map(admin,AdminDto.class);
//               }
//           }
//           catch (Exception e){
//               System.out.println(e);
//           }
//
//
//        System.out.println(admin);
//       return null;
        Admin admin=null;
        AdminDto adminDto=null;
        if(email==null){
            return null;
        }
        try{
            admin=repository.findByEmail(email);
            if(admin!=null){
                adminDto=mapper.map(admin, AdminDto.class);
                System.out.println(adminDto);
                return adminDto;
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println(adminDto);
            return adminDto;
            //return !email.isEmpty() ? userDto: null;
        }
        System.out.println(adminDto);
        return adminDto;
    }

    @Override
    public AdminDto findByEmailAndPassword(String email, String password) {
//        AdminDto adminDto=findByEmail(email);
//        System.out.println(adminDto);
//        if(adminDto!=null & passwordEncoder.matches(password,adminDto.getPassword())){
//            System.out.println(adminDto);
//            return adminDto;
//            //return mapper.map(adminDto, AdminDto.class);
//        }
//        return null;

        AdminDto adminDto=findByEmail(email);
        System.out.println(adminDto);
        if(adminDto!=null ){
            if(passwordEncoder.matches(password, adminDto.getPassword())){
                System.out.println(adminDto);
                return adminDto;
                //return mapper.map(adminDto, AdminDto.class);
            }

        }
        return null;
    }
}
