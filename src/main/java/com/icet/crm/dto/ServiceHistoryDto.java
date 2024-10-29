package com.icet.crm.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceHistoryDto {
    private Integer serviceId;
    private LocalDate serviceDate;
    private String description;
    private String nextServiceDate;
}
