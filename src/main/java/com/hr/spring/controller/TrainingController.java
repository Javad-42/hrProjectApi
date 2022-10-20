package com.hr.spring.controller;

import com.hr.spring.model.dto.TrainingDto;
import com.hr.spring.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainings")
public class TrainingController {
    private final TrainingService trainingService;

    @GetMapping
    public ResponseEntity<List<TrainingDto>> getTrainings() {
        return new ResponseEntity<>(trainingService.getTrainings(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TrainingDto> getTraining(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(trainingService.getTraining(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDTO) {
        return new ResponseEntity<>(trainingService.createTraining(trainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable("id") Integer id,
                                                      @RequestBody TrainingDto trainingDTO) {
        trainingService.updateTraining(id, trainingDTO);
        return new ResponseEntity<>(trainingDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTraining(@PathVariable("id") Integer id) {
        return trainingService.deleteByTraining(id);
    }
}
