package com.hr.spring.service;

import com.hr.spring.model.dto.CompanyDTO;
import com.hr.spring.model.entity.Company;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<CompanyDTO> getCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Company found"));
        return companyMapper.modelToDto(company);
    }

    public CompanyDTO createCompany(CompanyDTO companyDto) {
        Company company = companyMapper.dtoToModel(companyDto);
        companyRepository.save(company);
        return companyDto;
    }

    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Company found"));
        company.setName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        company.setRegistration_date(companyDTO.getRegistration_date());
        companyRepository.save(company);
        return companyDTO;
    }

    public ResponseEntity<HttpStatus> deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Company found");
        }
        companyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
