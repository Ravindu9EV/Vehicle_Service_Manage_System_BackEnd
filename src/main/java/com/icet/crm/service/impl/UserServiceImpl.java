package com.icet.crm.service.impl;

import com.icet.crm.dto.UserDto;
import com.icet.crm.entity.User;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    @Override
    public void addUser(UserDto user) {
        if(user!=null) repository.save(mapper.map(user, User.class));
    }

    @Override
    public boolean deleteUser(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public UserDto findById(Integer id) {
        User user=repository.getReferenceById(id);
        return user!=null ? mapper.map(user,UserDto.class):null;
    }

    @Override
    public UserDto findByEmail(String email) {
        return mapper.map(repository.findByEmail(email),UserDto.class);
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<UserDto> users=new ArrayList<>();
//        getAll().forEach(userDto -> {
//            if (userDto.getName().equals(name)){
//                users.add(userDto);
//            }
//        });
        for(User user:repository.findByName(name)){
            users.add(mapper.map(user,UserDto.class));
        }
        return users;

    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users=new ArrayList<>();
        repository.findAll().forEach(user -> users.add(mapper.map(user,UserDto.class)));
        return users;
    }

    @Override
    public void updateUser(UserDto user) {
        repository.save(mapper.map(user,User.class));
    }
}
