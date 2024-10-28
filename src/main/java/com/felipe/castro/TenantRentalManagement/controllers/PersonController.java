package com.felipe.castro.TenantRentalManagement.controllers;

import com.felipe.castro.TenantRentalManagement.dtos.PersonRecordDto;
import com.felipe.castro.TenantRentalManagement.services.PersonService;
import com.felipe.castro.TenantRentalManagement.utils.DocumentValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<Object> post(@RequestBody @Valid PersonRecordDto personRecordDto) {
        if (personRecordDto.id() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A new Person body should not contain an id.");
        }

        if (personRecordDto.cpf() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The CPF must be present.");
        }

        if (!DocumentValidator.isCpfValid(personRecordDto.cpf())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The given document is not valid.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personRecordDto));
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonRecordDto>> getAlL() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAll());
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Integer id) {
        PersonRecordDto person = personService.getOne(id);

        if (Objects.isNull(person)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PatchMapping("/persons/{id}")
    public ResponseEntity<Object> patch(@PathVariable(value = "id") Integer id,
                                        @RequestBody @Valid PersonRecordDto personRecordDto) {

        if (personRecordDto.cpf() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF should not be send to update.");
        }

        if (!id.equals(personRecordDto.id())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person id from path doesn't match the body id.");
        }

        PersonRecordDto updatedPerson = personService.update(personRecordDto);

        if (updatedPerson == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No person was found/updated");
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }
}