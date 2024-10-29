package com.icet.crm.repository;

import com.icet.crm.dto.BookingDto;
import com.icet.crm.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByBookedDate(String date);
    List<Booking> findByServiceId(Integer id);
    List<Booking> findByVehicleId(Integer id);
}
