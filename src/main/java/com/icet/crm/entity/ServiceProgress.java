package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ServiceProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="service_id")
    private Integer serviceId;
    private String status;
    private String date;
    private String description;
    private String time;
}
