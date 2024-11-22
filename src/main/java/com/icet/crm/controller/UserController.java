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
    @ResponseStatus(HttpStatus.OK)
    public boolean addUser(@RequestBody UserDto userDto){
        return service.addUser(userDto);
    }

    @GetMapping("/search-by-id/filter")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserById(@RequestParam Integer id){
       return service.findById(id);
    }

    @GetMapping("/search-by-email/filter")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserByEmail(@RequestParam String email){
        return service.findByEmail(email);
    }

    @GetMapping("/search-by-email-and-password/")
    @ResponseStatus(HttpStatus.OK)
    public UserDto searchUserByEmailAndPassword(@RequestParam String email,@RequestParam String password){
        UserDto userDto=service.findByEmailAndPassword(email, password);
        System.out.println(userDto);
        return userDto;
    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateUser(@RequestBody UserDto userDto){
        return service.updateUser(userDto);
    }
    @DeleteMapping("delete-by-id/filter")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestParam Integer id){
        service.deleteUser(id);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll(){
        return service.getAll();
    }
    @GetMapping("/get-user-by-name/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findByName(@RequestParam String name){
        return service.findByName(name);
    }

    @GetMapping("/reset-password/")
    public Integer resetPassword(String email){
        return service.resetPassword(email);
    }
}
