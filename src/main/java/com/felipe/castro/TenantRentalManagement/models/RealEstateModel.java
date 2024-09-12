package com.felipe.castro.TenantRentalManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "REALESTATE")
public class RealEstateModel extends RepresentationModel<RealEstateModel> implements Serializable {
    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue
    private UUID idRealEstate;
    private String rua;
    private int numero;

    public UUID getIdRealEstate() {
        return idRealEstate;
    }

    public void setIdRealEstate(UUID idRealEstate) {
        this.idRealEstate = idRealEstate;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
