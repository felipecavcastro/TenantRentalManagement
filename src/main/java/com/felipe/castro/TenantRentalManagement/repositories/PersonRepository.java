package com.felipe.castro.TenantRentalManagement.repositories;


import com.felipe.castro.TenantRentalManagement.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, UUID> {
}
