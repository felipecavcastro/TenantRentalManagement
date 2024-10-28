//package com.felipe.castro.TenantRentalManagement.services;
//
//import com.felipe.castro.TenantRentalManagement.dtos.RealEstateRecordDto;
//import com.felipe.castro.TenantRentalManagement.models.RealEstateModel;
//import com.felipe.castro.TenantRentalManagement.repositories.RealEstateRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class RealEstateService {
//
//    @Autowired
//    private RealEstateRepository realEstateRepository;
//
//    public RealEstateModel saveRealEstate(RealEstateRecordDto realEstateRecordDto) {
//        RealEstateModel realEstateModel = new RealEstateModel();
//        BeanUtils.copyProperties(realEstateRecordDto, realEstateModel);
//        return realEstateRepository.save(realEstateModel);
//    }
//
//    public List<RealEstateModel> getAllRealEstates() {
//        return realEstateRepository.findAll();
//    }
//
//    public Optional<RealEstateModel> getOneRealEstate(UUID id) {
//        return realEstateRepository.findById(id);
//    }
//
//    public RealEstateModel updateRealEstate(UUID id, RealEstateRecordDto realEstateRecordDto) {
//        Optional<RealEstateModel> realEstateO = realEstateRepository.findById(id);
//        if (realEstateO.isPresent()) {
//            RealEstateModel realEstateModel = realEstateO.get();
//            BeanUtils.copyProperties(realEstateRecordDto, realEstateModel);
//            return realEstateRepository.save(realEstateModel);
//        }
//        return null;
//    }
//
//    public boolean deleteRealEstate(UUID id) {
//        Optional<RealEstateModel> realEstateO = realEstateRepository.findById(id);
//        if (realEstateO.isPresent()) {
//            realEstateRepository.delete(realEstateO.get());
//            return true;
//        }
//        return false;
//    }
//}
