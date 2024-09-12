package com.felipe.castro.TenantRentalManagement.controllers;

import com.felipe.castro.TenantRentalManagement.dtos.PersonRecordDto;
import com.felipe.castro.TenantRentalManagement.models.PersonModel;
import com.felipe.castro.TenantRentalManagement.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("/persons")
    public ResponseEntity<PersonModel> savePerson(@RequestBody @Valid PersonRecordDto personRecordDto) {
        var personModel = new PersonModel();
        BeanUtils.copyProperties(personRecordDto, personModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(personModel));
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonModel>> getAllPersons() {
        List<PersonModel> personsList = personRepository.findAll();
        if (!personsList.isEmpty()){
            for (PersonModel person : personsList){
                UUID id = person.getIdPerson();
                person.add(linkTo(methodOn(PersonController.class).getOnePerson(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(personsList);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Object> getOnePerson(@PathVariable(value="id") UUID id){
        Optional<PersonModel> personO = personRepository.findById(id);
        if(personO.isEmpty())  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        personO.get().add(linkTo(methodOn(PersonController.class).getAllPersons()).withRel("Persons List"));
    return ResponseEntity.status(HttpStatus.OK).body(personO.get());
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value="id") UUID id,
                                               @RequestBody @Valid PersonRecordDto personRecordDto) {
        Optional<PersonModel> personO = personRepository.findById(id);
        if (personO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        var personModel = personO.get();
        BeanUtils.copyProperties(personRecordDto, personModel);
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(personModel));
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value="id")UUID id) {
        Optional<PersonModel> personO = personRepository.findById(id);
        if (personO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        personRepository.delete(personO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
        }
    }


