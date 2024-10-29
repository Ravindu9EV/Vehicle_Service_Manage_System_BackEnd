package com.icet.crm.service;

import com.icet.crm.dto.AdminDto;

import java.util.List;

public interface AdminService extends SuperService {
    void addAdmin(AdminDto adminDto);
    AdminDto findById(Integer id);
    boolean deleteById(Integer id);
    List<AdminDto> getAll();

}
