package com.icet.crm.service.impl;

import com.icet.crm.dto.AdminDto;
import com.icet.crm.entity.Admin;
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

       Admin admin=repository.findByEmail(email);
        System.out.println(admin);
       return admin==null ? null : mapper.map(admin,AdminDto.class);
    }
}
