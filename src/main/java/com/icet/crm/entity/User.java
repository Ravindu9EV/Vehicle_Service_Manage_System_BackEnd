package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String contact;
    private String email;
    private String password;
    @OneToMany(mappedBy = "User",cascade = CascadeType.ALL)
    private List<Vehicle> vehicleEntities;
}
