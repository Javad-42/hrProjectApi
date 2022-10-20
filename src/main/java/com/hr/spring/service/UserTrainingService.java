package com.hr.spring.service;

import com.hr.spring.model.dto.TrainingDto;
import com.hr.spring.model.dto.UserTrainingDto;
import com.hr.spring.model.entity.Training;
import com.hr.spring.model.entity.UserTraining;
import com.hr.spring.model.mapper.TrainingMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.model.mapper.UserTrainingMapper;
import com.hr.spring.repository.UserTrainingRepository;
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
public class UserTrainingService {
    private final UserTrainingRepository userTrainingRepository;
    private final UserTrainingMapper userTrainingMapper;
    private final UserService userService;
    private final TrainingMapper trainingMapper;
    private final TrainingService trainingService;
    private final UserMapper userMapper;

    public List<UserTrainingDto> getUT() {
        return userTrainingRepository.findAll()
                .stream()
                .map(userTrainingMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserTrainingDto getUT(Integer id) {
        UserTraining userTraining = userTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserTraining not found"));
        return userTrainingMapper.modelToDto(userTraining);
    }

    public UserTrainingDto create(UserTrainingDto userTrainingDTO) {
        UserTraining userTraining = userTrainingMapper.dtoToModel(userTrainingDTO);
        userTrainingRepository.save(userTraining);
        return userTrainingDTO;
    }

    public UserTrainingDto updateUT(Integer id, UserTrainingDto userTrainingDTO) {
        UserTraining userTraining = userTrainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserTraining not found"));
        userTraining.setStatus(userTrainingDTO.getStatus());
        userTraining.setStartDate(userTrainingDTO.getStartDate());
        userTraining.setEndDate(userTrainingDTO.getEndDate());
        userTrainingRepository.save(userTraining);
        return userTrainingDTO;
    }

    public ResponseEntity<HttpStatus> delete(Integer id) {
        if (!userTrainingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UserTraining not found");
        }
        userTrainingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<TrainingDto> getTrainingUser(Long id) {
        List<UserTraining> userTraining = userTrainingRepository.findAll()
                .stream()
                .filter(userTraining1 -> userTraining1.getUser().getId().equals(id))
                .collect(Collectors.toList());
        List<Training> trainings = new ArrayList<>();
        for (UserTraining userTraining1 : userTraining) {
            trainings.add(userTraining1.getTraining());
        }
        return trainings.stream()
                .map(trainingMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
