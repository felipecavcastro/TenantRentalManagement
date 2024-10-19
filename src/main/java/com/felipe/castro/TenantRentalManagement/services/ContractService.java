package com.felipe.castro.TenantRentalManagement.services;

import com.felipe.castro.TenantRentalManagement.dtos.ContractRecordDto;
import com.felipe.castro.TenantRentalManagement.models.ContractModel;
import com.felipe.castro.TenantRentalManagement.repositories.ContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public ContractModel saveContract(ContractRecordDto contractRecordDto) {
        ContractModel contractModel = new ContractModel();
        BeanUtils.copyProperties(contractRecordDto, contractModel);
        return contractRepository.save(contractModel);
    }

    public List<ContractModel> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<ContractModel> getOneContract(UUID id) {
        return contractRepository.findById(id);
    }

    public ContractModel updateContract(UUID id, ContractRecordDto contractRecordDto) {
        Optional<ContractModel> contractO = contractRepository.findById(id);
        if (contractO.isPresent()) {
            ContractModel contractModel = contractO.get();
            BeanUtils.copyProperties(contractRecordDto, contractModel);
            return contractRepository.save(contractModel);
        }
        return null;
    }

    public boolean deleteContract(UUID id) {
        Optional<ContractModel> contractO = contractRepository.findById(id);
        if (contractO.isPresent()) {
            contractRepository.delete(contractO.get());
            return true;
        }
        return false;
    }
}
