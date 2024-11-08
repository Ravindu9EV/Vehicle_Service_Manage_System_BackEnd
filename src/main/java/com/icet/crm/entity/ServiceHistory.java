package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "service_id",insertable = false,nullable = false,updatable = false)
    private Integer serviceId;
    private LocalDate serviceDate;
    private String description;
    private String nextServiceDate;

}
