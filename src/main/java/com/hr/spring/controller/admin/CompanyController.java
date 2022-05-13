package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.CompanyDTO;
import com.hr.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyService.getCompany(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO companyDto) {
        companyService.createCompany(companyDto);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id,
                                                    @RequestBody CompanyDTO companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") Long id) {
        return companyService.deleteCompany(id);
    }
}
