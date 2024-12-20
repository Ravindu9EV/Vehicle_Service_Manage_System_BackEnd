package com.icet.crm.controller;

import com.icet.crm.dto.LoginDto;
import com.icet.crm.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
@RequiredArgsConstructor
public class Logincontroller {
    final LoginService service;
    @GetMapping("/user-login/")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkUserLogin(@RequestParam String email,@RequestParam String password){

        return service.checkUserLogin(email, password);
    }
    @PostMapping("/admin-login")
    public boolean checkAdminLogin( @RequestBody LoginDto loginDto) {
        return service.checkAdminLogin(loginDto.getEmail(), loginDto.getPassword());
    }
}
