package com.felipe.castro.TenantRentalManagement.services;

import com.felipe.castro.TenantRentalManagement.dtos.PersonRecordDto;
import com.felipe.castro.TenantRentalManagement.models.PersonModel;
import com.felipe.castro.TenantRentalManagement.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonRecordDto create(PersonRecordDto personRecordDto) {
        PersonModel createdPerson = personRepository.save(fromDtoToModel(personRecordDto));
        return fromModelToDto(createdPerson);
    }

    private PersonModel fromDtoToModel(PersonRecordDto dto) {
        return PersonModel.builder()
                .idPerson(dto.id())
                .name(dto.name())
                .document(dto.cpf())
                .email(dto.email())
                .build();
    }

    private PersonRecordDto fromModelToDto(PersonModel model) {
        return new PersonRecordDto(model.getIdPerson(), model.getName(), model.getDocument(), model.getEmail());
    }

    public List<PersonRecordDto> getAll() {
        return personRepository.findAll().stream().map(this::fromModelToDto).toList();
    }

    public PersonRecordDto getOne(Integer id) {
        Optional<PersonModel> person = personRepository.findById(id);
        return person.map(this::fromModelToDto).orElse(null);
    }

    public PersonRecordDto update(PersonRecordDto personRecordDto) {
        Optional<PersonModel> person = personRepository.findById(personRecordDto.id());

        if (person.isEmpty()) {
            return null;
        }

        PersonModel existentPerson = person.get();

        existentPerson.setName(personRecordDto.name());
        existentPerson.setEmail(personRecordDto.email());

        PersonModel updatedPerson = personRepository.save(existentPerson);
        return fromModelToDto(updatedPerson);
    }
}
