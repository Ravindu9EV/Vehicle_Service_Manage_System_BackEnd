package com.icet.crm.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {
    private String email;
    private String password;
}
