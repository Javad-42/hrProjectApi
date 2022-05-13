package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.CompanyTrainingDTO;
import com.hr.spring.service.CompanyTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/company-trainings")
public class ACompanyTrainingController {
    private final CompanyTrainingService companyTrainingService;

    @GetMapping
    public ResponseEntity<List<CompanyTrainingDTO>> getTrainings() {
        return new ResponseEntity<>(companyTrainingService.getCompanyTrainings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyTrainingDTO> getTraining(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyTrainingService.getCT(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyTrainingDTO> create(@RequestBody CompanyTrainingDTO companyTrainingDTO) {
        return new ResponseEntity<>(companyTrainingService.createCT(companyTrainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyTrainingDTO> updateCT(@PathVariable("id") Long id,
                                                       @RequestBody CompanyTrainingDTO companyTrainingDTO) {
        return new ResponseEntity<>(companyTrainingService.update(id, companyTrainingDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return companyTrainingService.delete(id);
    }
}
