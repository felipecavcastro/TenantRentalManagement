package com.felipe.castro.TenantRentalManagement.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "CONTRACT")
public class ContractModel extends RepresentationModel<ContractModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idContract;

    @ManyToOne
    @JoinColumn(name = "idPerson", nullable = false)
    private PersonModel person;

    @ManyToOne
    @JoinColumn(name = "idRealEstate", nullable = false)
    private RealEstateModel realEstate;

    private LocalDate startDate;
    private LocalDate endDate;
    private double rentalValue;

    public UUID getIdContract() {
        return idContract;
    }

    public void setIdContract(UUID idContract) {
        this.idContract = idContract;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public RealEstateModel getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstateModel realEstate) {
        this.realEstate = realEstate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getRentalValue() {
        return rentalValue;
    }

    public void setRentalValue(double rentalValue) {
        this.rentalValue = rentalValue;
    }
}
