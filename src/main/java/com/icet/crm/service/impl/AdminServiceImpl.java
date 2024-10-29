package com.icet.crm.service.impl;

import com.icet.crm.dto.AdminDto;
import com.icet.crm.entity.Admin;
import com.icet.crm.repository.AdminRepository;
import com.icet.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class AdminServiceImpl implements AdminService {

        private final AdminRepository repository;
        private final ModelMapper mapper;

    @Override
    public void addAdmin(AdminDto adminDto) {
       if(adminDto!=null) repository.save(mapper.map(adminDto, Admin.class));
    }

    @Override
    public AdminDto findById(Integer id) {
       return mapper.map(repository.findById(id), AdminDto.class);
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<AdminDto> getAll() {
        List<AdminDto> admins=new ArrayList<>();
        repository.findAll().forEach(admin -> admins.add(mapper.map(admin, AdminDto.class)));
        return admins;
    }
}
