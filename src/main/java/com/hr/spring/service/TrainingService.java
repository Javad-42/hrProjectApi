package com.hr.spring.service;

import com.hr.spring.model.dto.TrainingDto;
import com.hr.spring.model.entity.Training;
import com.hr.spring.model.mapper.TrainingMapper;
import com.hr.spring.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public List<TrainingDto> getTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public TrainingDto getTraining(Integer id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Training not found"));
        return trainingMapper.modelToDto(training);
    }

    public TrainingDto createTraining(TrainingDto trainingDTO) {
        Training training = trainingMapper.dtoToModel(trainingDTO);
        trainingRepository.save(training);
        return trainingDTO;
    }

    public TrainingDto updateTraining(Integer id, TrainingDto trainingDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Training not found"));
        training.setName(trainingDTO.getName());
        training.setDescription(trainingDTO.getDescription());
        training.setVideoUrl(trainingDTO.getVideoUrl());
        training.setStatus(trainingDTO.getStatus());
        trainingRepository.save(training);
        return trainingDTO;
    }

    public ResponseEntity<HttpStatus> deleteByTraining(Integer id) {
        if (!trainingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Training not found");
        }
        trainingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
