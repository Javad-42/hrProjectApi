package com.hr.spring.controller.user;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.model.dto.CompanyDTO;
import com.hr.spring.model.dto.TrainingDTO;
import com.hr.spring.model.dto.UserDTO;
import com.hr.spring.security.services.UserDetailsImpl;
import com.hr.spring.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/profile")
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;
    private final UserTrainingService userTrainingService;
    private final UserExamService userExamService;

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetailsImpl user) {
        return new ResponseEntity<>(userService.getUser(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<CompanyDTO> getT(@AuthenticationPrincipal UserDetailsImpl user) {
        UserDTO userDTO = userService.getUser(user.getId());
        return new ResponseEntity<>(companyService.getCompany(userDTO.getCompanyId()), HttpStatus.OK);
    }

    @GetMapping("/trainings")
    public ResponseEntity<List<TrainingDTO>> getTraining(@AuthenticationPrincipal UserDetailsImpl user) {
        List<TrainingDTO> trainingDTOS = userTrainingService.getTrainingUser(user.getId());
        if (trainingDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(trainingDTOS, HttpStatus.OK);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<ExamDTO>> getExam(@AuthenticationPrincipal UserDetailsImpl user) {
        List<ExamDTO> examDTOS = userExamService.getExamUser(user.getId());
        if (examDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(examDTOS, HttpStatus.OK);
    }

    @PostMapping("/exams/{id}")
    public ResponseEntity<HttpStatus> postScore(@AuthenticationPrincipal UserDetailsImpl user,
                                                @PathVariable("id") Long id,
                                                @RequestBody Short score) {
        return userExamService.postAnswer(id, user.getId(), score);
    }
}
