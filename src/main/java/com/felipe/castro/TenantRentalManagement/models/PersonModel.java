package com.felipe.castro.TenantRentalManagement.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PERSONS")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerson;
    private String name;
    private String document;
    private String email;
}
