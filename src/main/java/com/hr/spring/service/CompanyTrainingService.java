package com.hr.spring.service;

import com.hr.spring.model.dto.CompanyTrainingDto;
import com.hr.spring.model.dto.TrainingDto;
import com.hr.spring.model.entity.CompanyTraining;
import com.hr.spring.model.entity.Training;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.model.mapper.CompanyTrainingMapper;
import com.hr.spring.model.mapper.TrainingMapper;
import com.hr.spring.repository.CompanyTrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<CompanyTrainingDto> getCompanyTrainings() {
        return companyTrainingRepository.findAll()
                .stream()
                .map(companyTrainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public CompanyTrainingDto getCT(Integer id) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"CompanyTraining not found"));
        return companyTrainingMapper.modelToDto(companyTraining);
    }

    public CompanyTrainingDto createCT(CompanyTrainingDto companyTrainingDTO) {
        companyTrainingRepository.save(companyTrainingMapper.dtoToModel(companyTrainingDTO));
        return companyTrainingDTO;
    }

    public CompanyTrainingDto update(Integer id, CompanyTrainingDto companyTrainingDTO) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        companyTraining.setBuyDate(companyTrainingDTO.getBuyDate());

        companyTrainingRepository.save(companyTraining);
        return companyTrainingDTO;
    }

    public ResponseEntity<HttpStatus> delete(Integer id) {
        if (!companyTrainingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CompanyTraining not found");
        }
        companyTrainingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------ Moderator -------------

    /**
     * Company-ə mexsus training-lər
     *
     * @param companyId
     * @return TrainingDTO
     */
    public List<TrainingDto> getTr(Long companyId) {
        List<CompanyTraining> trainings = companyTrainingRepository.findAll()
                .stream()
                .filter(x -> x.getCompany().getId().equals(companyId))
                .collect(Collectors.toList());
        List<Training> trainings1 = new ArrayList<>();
        trainings.forEach(x -> trainings1.add(x.getTraining()));
        return trainings1
                .stream()
                .map(trainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public CompanyTrainingDto getCT(Integer companyId, Integer id) {
        CompanyTraining companyTraining = companyTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!companyTraining.getCompany().getId().equals(companyId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        return companyTrainingMapper.modelToDto(companyTraining);
    }
}
