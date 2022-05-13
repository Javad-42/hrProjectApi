package com.hr.spring.service;

import com.hr.spring.model.dto.CompanyTrainingDTO;
import com.hr.spring.model.dto.TrainingDTO;
import com.hr.spring.model.entity.CompanyTraining;
import com.hr.spring.model.entity.Training;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.model.mapper.CompanyTrainingMapper;
import com.hr.spring.model.mapper.TrainingMapper;
import com.hr.spring.repository.CompanyTrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyTrainingService {
    private final CompanyTrainingRepository companyTrainingRepository;
    private final CompanyTrainingMapper companyTrainingMapper;
    private final TrainingService trainingService;
    private final CompanyService companyService;
    private final TrainingMapper trainingMapper;
    private final CompanyMapper companyMapper;

    public List<CompanyTrainingDTO> getCompanyTrainings() {
        return companyTrainingRepository.findAll()
                .stream()
                .map(companyTrainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public CompanyTrainingDTO getCT(Long id) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"CompanyTraining not found"));
        return companyTrainingMapper.modelToDto(companyTraining);
    }

    public CompanyTrainingDTO createCT(CompanyTrainingDTO companyTrainingDTO) {
        companyTrainingRepository.save(companyTrainingMapper.dtoToModel(companyTrainingDTO));
        return companyTrainingDTO;
    }

    public CompanyTrainingDTO update(Long id, CompanyTrainingDTO companyTrainingDTO) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        companyTraining.setBuy_date(companyTrainingDTO.getBuy_date());
        companyTraining.setTrainings(trainingMapper.dtoToModel(trainingService.getTraining(companyTrainingDTO.getTrainingId())));
        companyTraining.setCompanies(companyMapper.dtoToModel(companyService.getCompany(companyTrainingDTO.getCompanyId())));
        companyTrainingRepository.save(companyTraining);
        return companyTrainingDTO;
    }

    public ResponseEntity<HttpStatus> delete(Long id) {
        if (!companyTrainingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTraining not found");
        }
        companyTrainingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------ Moderator -------------

    /**
     * Company-ə məxsus training-lər
     *
     * @param companyId
     * @return TrainingDTO
     */
    public List<TrainingDTO> getTr(Long companyId) {
        List<CompanyTraining> trainings = companyTrainingRepository.findAll()
                .stream()
                .filter(x -> x.getCompanies().getId().equals(companyId))
                .collect(Collectors.toList());
        List<Training> trainings1 = new ArrayList<>();
        trainings.forEach(x -> trainings1.add(x.getTrainings()));
        return trainings1
                .stream()
                .map(trainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public CompanyTrainingDTO getCT(Long companyId, Long id) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!companyTraining.getCompanies().getId().equals(companyId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        return companyTrainingMapper.modelToDto(companyTraining);
    }
}
