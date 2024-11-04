package com.icet.crm.controller;

import com.icet.crm.dto.UserDto;
import com.icet.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public void addUser(@RequestBody UserDto userDto){
        service.addUser(userDto);
    }

    @GetMapping("/search-by-id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto searchUserById(@PathVariable Integer id){
       return service.findById(id);
    }

    @GetMapping("/search-by-email/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto searchUserByEmail(@PathVariable String email){
        return service.findByEmail(email);
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
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> getAll(){
        return service.getAll();
    }
    @GetMapping("/get-user-by-name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> findByName(@PathVariable String name){
        return service.findByName(name);
    }
}
