package com.hr.spring.controller;

import com.hr.spring.model.dto.ExamDto;
import com.hr.spring.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDto>> getExams() {
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExam(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(examService.getExam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto exam) {
        return new ResponseEntity<>(examService.createExam(exam), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> updateExam(@PathVariable("id") Integer id,
                                              @RequestBody ExamDto exam) {
        examService.updateExam(id, exam);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExam(@PathVariable("id") Integer id) {
        return examService.deleteByExam(id);
    }

}
