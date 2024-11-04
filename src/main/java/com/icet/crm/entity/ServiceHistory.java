package com.icet.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Integer id;
    @Column(name = "service_id",insertable = false,nullable = false,updatable = false)
    private Integer serviceId;
    private LocalDate serviceDate;
    private String description;
    private String nextServiceDate;

}
