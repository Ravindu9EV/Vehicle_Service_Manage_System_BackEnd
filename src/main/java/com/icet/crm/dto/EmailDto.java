package com.icet.crm.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailDto {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private String date;
}
