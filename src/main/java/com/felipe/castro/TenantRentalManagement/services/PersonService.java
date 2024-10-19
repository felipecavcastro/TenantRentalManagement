package com.felipe.castro.TenantRentalManagement.services;

import com.felipe.castro.TenantRentalManagement.dtos.PersonRecordDto;
import com.felipe.castro.TenantRentalManagement.models.PersonModel;
import com.felipe.castro.TenantRentalManagement.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonModel savePerson(PersonRecordDto personRecordDto) {
        PersonModel personModel = new PersonModel();
        BeanUtils.copyProperties(personRecordDto, personModel);
        return personRepository.save(personModel);
    }

    public List<PersonModel> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<PersonModel> getOnePerson(UUID id) {
        return personRepository.findById(id);
    }

    public PersonModel updatePerson(UUID id, PersonRecordDto personRecordDto) {
        Optional<PersonModel> personO = personRepository.findById(id);
        if (personO.isPresent()) {
            PersonModel personModel = personO.get();
            BeanUtils.copyProperties(personRecordDto, personModel);
            return personRepository.save(personModel);
        }
        return null;
    }

    public boolean deletePerson(UUID id) {
        Optional<PersonModel> personO = personRepository.findById(id);
        if (personO.isPresent()) {
            personRepository.delete(personO.get());
            return true;
        }
        return false;
    }
}
