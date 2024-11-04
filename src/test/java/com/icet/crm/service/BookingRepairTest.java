package com.icet.crm.service;


import com.icet.crm.dto.BookingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingRepairTest {
    @Autowired
    BookingService service;

    @Test
    void findByVehicleId_is_Not_in_system(){
        List<BookingDto> b=service.findByVehicleId(1);
        List <BookingDto> n=new ArrayList<>();
        n.add(new BookingDto(1,"","",2,""));
        n.add(new BookingDto(1,"","",3,""));

        Assertions.assertEquals(n,b);
    }

    @Test
    void findByVehicleId_is_found(){
        List<BookingDto> b=service.findByVehicleId(1);
        List <BookingDto> n=new ArrayList<>();
        n.add(new BookingDto(1,"","",2,""));
        n.add(new BookingDto(1,"","",3,""));
        n.add(new BookingDto(2,"","",1,""));
        Assertions.assertEquals(n,new BookingDto(2,"","",1,""));
    }
}
