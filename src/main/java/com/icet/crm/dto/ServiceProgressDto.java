package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceProgressDto {
    private String serviceId;
    private String status;
    private String date;
    private String description;
    private String time;
}
