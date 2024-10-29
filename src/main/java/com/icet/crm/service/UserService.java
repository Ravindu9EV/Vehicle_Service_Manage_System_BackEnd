package com.icet.crm.service;

import com.icet.crm.dto.UserDto;

import java.util.List;

public interface UserService extends SuperService {
    void addUser(UserDto user);
    boolean deleteUser(Integer id);
    UserDto findById(Integer id);
    List<UserDto> findByName(String name);
    List<UserDto> getAll();

}
