package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VehicleDto {
    private Integer id;
    private Integer userId;
    private String model;
    private String licensePlate;
    private String madeYear;
}
