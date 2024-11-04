package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer vehicleId;
    private String bookedDate;
    private String bookedTime;
    private Integer repairId;
    private String description;
}
