package com.icet.crm.controller;

import com.icet.crm.dto.AdminDto;
import com.icet.crm.dto.LoginDto;
import com.icet.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    @PostMapping("/add-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdmin(@RequestBody AdminDto adminDto){

        service.addAdmin(adminDto);
    }
    @GetMapping("/search-admin/{id}")
    public AdminDto searchAdmin(@PathVariable Integer id){
        return service.findById(id);
    }

    @DeleteMapping("/delete-admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteAdmin(@PathVariable Integer id){
        return service.deleteById(id);
    }

    @GetMapping("/get-all")
    public List<AdminDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/search-admin-by-email")
    public AdminDto searchAdminByEmailAndPassword(@RequestBody LoginDto loginDto){

        return service.findByEmail(loginDto.getEmail());
    }

}
