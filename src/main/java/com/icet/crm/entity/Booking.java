package com.icet.crm.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="vehicle_id")
    private Integer vehicleId;
    private String bookedDate;
    private String bookedTime;
    @Column(name="repair_id")
    private Integer repairId;
    private String description;


    private void setId(Integer id){
        this.id=id;
    }

}
