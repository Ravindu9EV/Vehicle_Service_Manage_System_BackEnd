package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDto {
    private Integer vehicleId;
    private String bookedDate;
    private String bookedTime;
    private Integer repairId;
    private String description;
}
