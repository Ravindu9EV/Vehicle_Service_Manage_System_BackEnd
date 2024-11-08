package com.icet.crm.service.impl;

import com.icet.crm.dto.AdminDto;
import com.icet.crm.dto.UserDto;
import com.icet.crm.repository.AdminRepository;
import com.icet.crm.repository.UserRepository;
import com.icet.crm.service.AdminService;
import com.icet.crm.service.LoginService;
import com.icet.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    final AdminService adminService;
    final UserService userService;
    final PasswordEncoder passwordEncoder;
    public boolean checkUserLogin(String email,String password){
        UserDto userDto=userService.findByEmail(email);
        if(userDto!=null & passwordEncoder.matches(password,userDto.getPassword())){
            return true;
        }
         return false;

    }

    @Override
    public boolean checkAdminLogin(String email, String password) {

        AdminDto adminDto= adminService.findByEmail(email) ;
        if(adminDto!=null & passwordEncoder.matches(password,adminDto.getPassword())){
            return true;
        }
        return false;
    }
}
