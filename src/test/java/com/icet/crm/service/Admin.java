package com.icet.crm.service;

import com.icet.crm.dto.AdminDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Admin {
    @Autowired
    AdminService adminService;
 //   @Test
//    void check_whenEmailAndPassword_is_correct(){
//        String email="yravindu18@gmail.com";
//        String password="password123";
//        AdminDto adminDto=new AdminDto("Yasantha Ravindu",
//                "217381", "yravindu18@gmail.com","$2a$10$E2enQM6zyY8JZ0G22vrEBe4EViRlLwJVehKX/lNcejs5jd7huhK.S");
//        Assertions.assertEquals(adminDto,adminDto);
//
//    }
//    @Test
//    void check_whenPassword_is_wrong_and_email_is_correct(){
//        String email="yravindu18@gmail.com";
//        String password="password13";
//        AdminDto adminDto=new AdminDto("Yasantha Ravindu",
//                "217381", "yravindu18@gmail.com","$2a$10$E2enQM6zyY8JZ0G22vrEBe4EViRlLwJVehKX/lNcejs5jd7huhK.S");
//        Assertions.assertEquals(adminDto,adminDto);
//
//    }
//    @Test
//    void whenEmail_is_wrong_and_password_is_correct(){
//        String email="yravndu18@gmail.com";
//        String password="password123";
//        AdminDto adminDto=new AdminDto("Yasantha Ravindu",
//                "217381", "yravindu18@gmail.com","$2a$10$E2enQM6zyY8JZ0G22vrEBe4EViRlLwJVehKX/lNcejs5jd7huhK.S");
//        Assertions.assertEquals(adminDto,adminDto);
//
//    }
//    @Test
//    void check_whenEmailAndPassword_is_Empty(){
//        String email=null;
//        String password=null;
//        AdminDto adminDto=null;
//        Assertions.assertEquals(adminDto,adminDto);
//
//    }
//    @Test
//    void check_whenEmailis_Null(){
//        String email=null;
//        String password="password123";
//        AdminDto adminDto=null;
//        Assertions.assertEquals(null,adminDto);
//
//    }
//    @Test
//    void check_whenPasswordis_Null(){
//        String email="yravindu18@gmail.com";
//        String password=null;
//        AdminDto adminDto=null;
//        Assertions.assertEquals(null,adminDto);
//
//    }
}
