package com.hr.spring.service;


import com.hr.spring.model.dto.ExamDto;
import com.hr.spring.model.dto.UserExamDto;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.entity.UserExam;
import com.hr.spring.model.mapper.ExamMapper;
import com.hr.spring.model.mapper.UserExamMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.repository.UserExamRepository;
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

    public List<UserExamDto> getUserExams() {
        return userExamRepository.findAll()
                .stream()
                .map(userExamMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserExamDto getUserExam(Integer id) {
        UserExam userExam = userExamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserExam not found"));
        return userExamMapper.modelToDto(userExam);
    }

    public UserExamDto createUserExam(UserExamDto userExamDTO) {
        UserExam userExam = userExamMapper.dtoToModel(userExamDTO);
        userExamRepository.save(userExam);
        return userExamDTO;
    }

    public UserExamDto updateUserExam(Integer id, UserExamDto userExamDTO) {
        UserExam userExam = userExamRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userExam.setUserScore(userExamDTO.getUserScore());
        userExamRepository.save(userExam);
        return userExamDTO;
    }

    public ResponseEntity<HttpStatus> deleteUserExam(Integer id) {
        if (!userExamRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<ExamDto> getExamUser(Long id) {
        List<UserExam> userExams = userExamRepository.findAll()
                .stream()
                .filter(userExam -> userExam.getUser().getId().equals(id))
                .collect(Collectors.toList());
        List<Exam> exams = new ArrayList<>();
        for (UserExam userExam : userExams) {
            exams.add(userExam.getExam());
        }
        return exams.stream().map(examMapper::modelToDto).collect(Collectors.toList());
    }

    public ResponseEntity<HttpStatus> postAnswer(Long id, Long userId, Short score) {
        List<UserExam> userExams = userExamRepository.findAll();
        for (UserExam userExam : userExams) {
            if (userExam.getUser().getId().equals(userId) && userExam.getExam().getId().equals(id)) {
                userExam.setUserScore(score);
                userExamRepository.save(userExam);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
