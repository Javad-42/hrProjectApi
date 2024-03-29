package com.hr.spring.controller;

import com.hr.spring.model.dto.UserExamDto;
import com.hr.spring.service.UserExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-exams")
public class UserExamController {
    private final UserExamService userExamService;

    @GetMapping
    public ResponseEntity<List<UserExamDto>> getUserExams() {
        return new ResponseEntity<>(userExamService.getUserExams(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserExamDto> getUserExam(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userExamService.getUserExam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserExamDto> createUserExam(@RequestBody UserExamDto userExam) {
        return new ResponseEntity<>(userExamService.createUserExam(userExam), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserExamDto> updateUserExam(@PathVariable("id") Integer id,
                                                      @RequestBody UserExamDto userExam) {
        return new ResponseEntity<>(userExamService.updateUserExam(id, userExam), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUserExam(@PathVariable("id") Integer id) {
        return userExamService.deleteUserExam(id);
    }
}
