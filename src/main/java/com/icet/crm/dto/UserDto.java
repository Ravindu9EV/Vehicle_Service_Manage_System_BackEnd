package com.icet.crm.dto;

import com.icet.crm.entity.Vehicle;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String name;
    private String contact;
    private String email;
    private String password;
    private List<Vehicle> vehicleEntities;
}
