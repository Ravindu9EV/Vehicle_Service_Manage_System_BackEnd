package com.icet.crm.repository;

import com.icet.crm.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email,Integer> {
    List<Email> findBySender(String sender);
    List<Email> findByRecipient(String recipient);
    List<Email> findByDateAndSender(String date, String sender );
    List<Email> findByDateAndRecipient(String date,String recipient);
}
