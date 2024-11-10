package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepairDto {
    private Integer id;
    private String type;
    private String cost;
    private String duration;
    private String description;
}
