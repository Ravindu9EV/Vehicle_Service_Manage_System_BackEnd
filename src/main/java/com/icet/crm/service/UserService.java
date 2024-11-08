package com.icet.crm.service;

import com.icet.crm.dto.UserDto;

import java.util.List;

public interface UserService {
    boolean addUser(UserDto user);
    boolean deleteUser(Integer id);
    UserDto findById(Integer id);
    UserDto findByEmail(String email);
    List<UserDto> findByName(String name);
    List<UserDto> getAll();
    void updateUser(UserDto user);

}
