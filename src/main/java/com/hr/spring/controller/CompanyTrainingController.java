package com.hr.spring.controller;

import com.hr.spring.model.dto.CompanyTrainingDto;
import com.hr.spring.service.CompanyTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company-trainings")
public class CompanyTrainingController {
    private final CompanyTrainingService companyTrainingService;

    @GetMapping
    public ResponseEntity<List<CompanyTrainingDto>> getTrainings() {
        return new ResponseEntity<>(companyTrainingService.getCompanyTrainings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyTrainingDto> getTraining(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(companyTrainingService.getCT(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyTrainingDto> create(@RequestBody CompanyTrainingDto companyTrainingDTO) {
        return new ResponseEntity<>(companyTrainingService.createCT(companyTrainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyTrainingDto> updateCT(@PathVariable("id") Integer id,
                                                       @RequestBody CompanyTrainingDto companyTrainingDTO) {
        return new ResponseEntity<>(companyTrainingService.update(id, companyTrainingDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        return companyTrainingService.delete(id);
    }
}
