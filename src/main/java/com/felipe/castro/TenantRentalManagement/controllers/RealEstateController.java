//package com.felipe.castro.TenantRentalManagement.controllers;
//
//import com.felipe.castro.TenantRentalManagement.dtos.RealEstateRecordDto;
//import com.felipe.castro.TenantRentalManagement.models.RealEstateModel;
//import com.felipe.castro.TenantRentalManagement.repositories.RealEstateRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
//@RestController
//public class RealEstateController {
//
//    @Autowired
//    RealEstateRepository realEstateRepository;
//
//    @PostMapping("/realestate")
//    public ResponseEntity<RealEstateModel> saveRealEstate(@RequestBody @Valid RealEstateRecordDto realEstateRecordDto) {
//        var realEstateModel = new RealEstateModel();
//        BeanUtils.copyProperties(realEstateRecordDto, realEstateModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(realEstateRepository.save(realEstateModel));
//    }
//
//    @GetMapping("/realestate")
//    public ResponseEntity<List<RealEstateModel>> getAllRealEstates() {
//        List<RealEstateModel> realEstatesList = realEstateRepository.findAll();
//        if (!realEstatesList.isEmpty()){
//            for (RealEstateModel realEstate : realEstatesList) {
//                UUID id = realEstate.getIdRealEstate();
//                realEstate.add(linkTo(methodOn(RealEstateController.class).getOneRealEstate(id)).withSelfRel());
//            }
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(realEstatesList);
//    }
//
//    @GetMapping("/realestate/{id}")
//    public ResponseEntity<Object> getOneRealEstate(@PathVariable(value="id") UUID id){
//        Optional<RealEstateModel> realEstateO = realEstateRepository.findById(id);
//        if(realEstateO.isEmpty())  {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real Estate not found.");
//        }
//        realEstateO.get().add(linkTo(methodOn(RealEstateController.class).getAllRealEstates()).withRel("Real Estate List"));
//        return ResponseEntity.status(HttpStatus.OK).body(realEstateO.get());
//    }
//
//    @PutMapping("/realestate/{id}")
//    public ResponseEntity<Object> updateRealEstate(@PathVariable(value="id") UUID id,
//                                               @RequestBody @Valid RealEstateRecordDto realEstateRecordDto) {
//        Optional<RealEstateModel> realEstateO = realEstateRepository.findById(id);
//        if (realEstateO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real Estate not found.");
//        }
//        var realEstateModel = realEstateO.get();
//        BeanUtils.copyProperties(realEstateRecordDto, realEstateModel);
//        return ResponseEntity.status(HttpStatus.OK).body(realEstateRepository.save(realEstateModel));
//    }
//
//    @DeleteMapping("/realestate/{id}")
//    public ResponseEntity<Object> deleteRealEstate(@PathVariable(value="id")UUID id) {
//        Optional<RealEstateModel> realEstateO = realEstateRepository.findById(id);
//        if (realEstateO.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Real Estate not found.");
//        }
//        realEstateRepository.delete(realEstateO.get());
//        return ResponseEntity.status(HttpStatus.OK).body("Real Estate deleted successfully.");
//    }
//}
