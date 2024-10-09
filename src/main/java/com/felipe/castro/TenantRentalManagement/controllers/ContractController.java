package com.felipe.castro.TenantRentalManagement.controllers;

import com.felipe.castro.TenantRentalManagement.dtos.ContractRecordDto;
import com.felipe.castro.TenantRentalManagement.models.ContractModel;
import com.felipe.castro.TenantRentalManagement.repositories.ContractRepository;
import com.felipe.castro.TenantRentalManagement.repositories.PersonRepository;
import com.felipe.castro.TenantRentalManagement.repositories.RealEstateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContractController {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RealEstateRepository realEstateRepository;

    @PostMapping("/contracts")
    public ResponseEntity<Object> saveContract(@RequestBody @Valid ContractRecordDto contractRecordDto) {
        var contractModel = new ContractModel();

        // Verifica se a pessoa existe
        Optional<PersonModel> person = personRepository.findById(contractRecordDto.getPersonId());
        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }

        // Verifica se o imóvel existe
        Optional<RealEstateModel> realEstate = realEstateRepository.findById(contractRecordDto.getRealEstateId());
        if (realEstate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real estate not found.");
        }

        // Copia as propriedades do DTO para o model
        BeanUtils.copyProperties(contractRecordDto, contractModel);
        contractModel.setPerson(person.get());
        contractModel.setRealEstate(realEstate.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(contractRepository.save(contractModel));
    }

    @GetMapping("/contracts")
    public ResponseEntity<List<ContractModel>> getAllContracts() {
        List<ContractModel> contractsList = contractRepository.findAll();
        if (!contractsList.isEmpty()) {
            for (ContractModel contract : contractsList) {
                UUID id = contract.getIdContract();
                contract.add(linkTo(methodOn(ContractController.class).getOneContract(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(contractsList);
    }

    @GetMapping("/contracts/{id}")
    public ResponseEntity<Object> getOneContract(@PathVariable(value = "id") UUID id) {
        Optional<ContractModel> contractO = contractRepository.findById(id);
        if (contractO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contract not found.");
        }
        contractO.get().add(linkTo(methodOn(ContractController.class).getAllContracts()).withRel("Contracts List"));
        return ResponseEntity.status(HttpStatus.OK).body(contractO.get());
    }

    @PutMapping("/contracts/{id}")
    public ResponseEntity<Object> updateContract(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid ContractRecordDto contractRecordDto) {
        Optional<ContractModel> contractO = contractRepository.findById(id);
        if (contractO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contract not found.");
        }

        // Verifica se a pessoa e o imóvel existem
        Optional<PersonModel> person = personRepository.findById(contractRecordDto.getPersonId());
        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }

        Optional<RealEstateModel> realEstate = realEstateRepository.findById(contractRecordDto.getRealEstateId());
        if (realEstate.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real estate not found.");
        }

        var contractModel = contractO.get();
        BeanUtils.copyProperties(contractRecordDto, contractModel);
        contractModel.setPerson(person.get());
        contractModel.setRealEstate(realEstate.get());

        return ResponseEntity.status(HttpStatus.OK).body(contractRepository.save(contractModel));
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Object> deleteContract(@PathVariable(value = "id") UUID id) {
        Optional<ContractModel> contractO = contractRepository.findById(id);
        if (contractO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contract not found.");
        }
        contractRepository.delete(contractO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contract deleted successfully.");
    }
}
