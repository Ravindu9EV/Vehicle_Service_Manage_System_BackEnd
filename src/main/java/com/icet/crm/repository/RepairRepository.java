package com.icet.crm.repository;

import com.icet.crm.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair,Integer> {
    List<Repair> findByType(String type);
    Repair findRepairByType(String type);
}
