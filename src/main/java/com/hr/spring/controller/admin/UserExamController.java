package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.UserExamDTO;
import com.hr.spring.service.UserExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user-exams")
public class UserExamController {
    private final UserExamService userExamService;

    @GetMapping
    public ResponseEntity<List<UserExamDTO>> getUserExams() {
        return new ResponseEntity<>(userExamService.getUserExams(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserExamDTO> getUserExam(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userExamService.getUserExam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserExamDTO> createUserExam(@RequestBody UserExamDTO userExam) {
        return new ResponseEntity<>(userExamService.createUserExam(userExam), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserExamDTO> updateUserExam(@PathVariable("id") Long id,
                                                      @RequestBody UserExamDTO userExam) {
        return new ResponseEntity<>(userExamService.updateUserExam(id, userExam), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUserExam(@PathVariable("id") Long id) {
        return userExamService.deleteUserExam(id);
    }
}
