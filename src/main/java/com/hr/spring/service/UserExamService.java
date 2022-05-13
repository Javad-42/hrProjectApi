package com.hr.spring.service;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.model.dto.UserExamDTO;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.entity.UserExam;
import com.hr.spring.model.mapper.ExamMapper;
import com.hr.spring.model.mapper.UserExamMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.repository.examsRepository.UserExamRepository;
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
public class UserExamService {
    private final UserExamRepository userExamRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final ExamService examService;
    private final ExamMapper examMapper;
    private final UserExamMapper userExamMapper;

    public List<UserExamDTO> getUserExams() {
        return userExamRepository.findAll()
                .stream()
                .map(userExamMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserExamDTO getUserExam(Long id) {
        UserExam userExam = userExamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserExam not found"));
        return userExamMapper.modelToDto(userExam);
    }

    public UserExamDTO createUserExam(UserExamDTO userExamDTO) {
        UserExam userExam = userExamMapper.dtoToModel(userExamDTO);
        userExamRepository.save(userExam);
        return userExamDTO;
    }

    public UserExamDTO updateUserExam(Long id, UserExamDTO userExamDTO) {
        UserExam userExam = userExamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userExam.setUser_score(userExamDTO.getUser_score());
        userExam.setUserE(userMapper.dtoToModel(userService.getUser(userExamDTO.getUserId())));
        userExam.setExams(examMapper.dtoToModel(examService.getExam(userExamDTO.getExamId())));
        userExamRepository.save(userExam);
        return userExamDTO;
    }

    public ResponseEntity<HttpStatus> deleteUserExam(Long id) {
        if (!userExamRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ExamDTO> getExamUser(Long id) {
        List<UserExam> userExams = userExamRepository.findAll()
                .stream()
                .filter(userExam -> userExam.getUserE().getId().equals(id))
                .collect(Collectors.toList());
        List<Exam> exams = new ArrayList<>();
        for (UserExam userExam : userExams) {
            exams.add(userExam.getExams());
        }
        return exams.stream().map(examMapper::modelToDto).collect(Collectors.toList());
    }

    public ResponseEntity<HttpStatus> postAnswer(Long id, Long userId, Short score) {
        List<UserExam> userExams = userExamRepository.findAll();
        for (UserExam userExam : userExams) {
            if (userExam.getUserE().getId().equals(userId) && userExam.getExams().getId().equals(id)) {
                userExam.setUser_score(score);
                userExamRepository.save(userExam);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
