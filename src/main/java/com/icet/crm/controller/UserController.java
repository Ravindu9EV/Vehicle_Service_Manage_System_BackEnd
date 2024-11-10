package com.icet.crm.controller;

import com.icet.crm.dto.LoginDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addUser(@RequestBody UserDto userDto){
        return service.addUser(userDto);
    }

    @GetMapping("/search-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserById(@PathVariable Integer id){
       return service.findById(id);
    }

    @GetMapping("/search-by-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserByEmail(@PathVariable String email){
        return service.findByEmail(email);
    }

    @GetMapping("/search-by-email-and-password")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserByEmailAndPassword(@RequestBody LoginDto loginDto){
        UserDto userDto=service.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        System.out.println(userDto);
        return userDto;
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody UserDto userDto){
        service.updateUser(userDto);
    }
    @DeleteMapping("delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll(){
        return service.getAll();
    }
    @GetMapping("/get-user-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findByName(@PathVariable String name){
        return service.findByName(name);
    }
}
