package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private User user;
    @Column(name = "user_id",insertable = false,nullable = false,updatable = false)
    private Integer userId;
    private String model;
    private String licensePlate;
    private String madeYear;
}
