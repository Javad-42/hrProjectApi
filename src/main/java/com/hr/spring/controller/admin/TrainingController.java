package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.TrainingDTO;
import com.hr.spring.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/trainings")
public class TrainingController {
    private final TrainingService trainingService;

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> getTrainings() {
        return new ResponseEntity<>(trainingService.getTrainings(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TrainingDTO> getTraining(@PathVariable("id") Long id) {
        return new ResponseEntity<>(trainingService.getTraining(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDTO) {
        return new ResponseEntity<>(trainingService.createTraining(trainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable("id") Long id,
                                                      @RequestBody TrainingDTO trainingDTO) {
        trainingService.updateTraining(id, trainingDTO);
        return new ResponseEntity<>(trainingDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTraining(@PathVariable("id") Long id) {
        return trainingService.deleteByTraining(id);
    }
}
