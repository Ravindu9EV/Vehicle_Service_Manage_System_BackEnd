package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {
    private String name;
    private String contact;
    private String email;
    private String password;
}
