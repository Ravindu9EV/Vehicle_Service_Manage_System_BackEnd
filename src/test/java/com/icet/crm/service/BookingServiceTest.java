package com.icet.crm.service;

import com.icet.crm.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingServiceTest {
    @Autowired
    BookingServiceImpl bookingService;
    @Test
    void testBookingWhenIncorrectYearProvided(){
        Assertions.assertEquals(false,bookingService.checkDate("2026-11-20"));
    }
    @Test
    void testBookingWhenCorrectYearAndIncorrectMonthProvided(){
        Assertions.assertEquals(false,bookingService.checkDate("2024-10-20"));
      //  Assertions.assertEquals(false,bookingService.checkDate("2025-01-20"));
    }
    @Test
    void testBookingWhenCurrentYearBelowBookedYearAndCurrentMonthEquals12AndBookedMonthProvidedAs1(){
        Assertions.assertEquals(true,bookingService.checkDate("2025-01-20"));
        //  Assertions.assertEquals(false,bookingService.checkDate("2025-01-20"));
    }
    @Test
    void testBookingWhenCorrectYearProvided(){
        Assertions.assertEquals(true,bookingService.checkDate("2024-11-21"));
    }
}
