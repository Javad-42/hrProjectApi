package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getExams() {
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExam(@PathVariable("id") Long id) {
        return new ResponseEntity<>(examService.getExam(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO exam) {
        return new ResponseEntity<>(examService.createExam(exam), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable("id") Long id,
                                              @RequestBody ExamDTO exam) {
        examService.updateExam(id, exam);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExam(@PathVariable("id") Long id) {
        return examService.deleteByExam(id);
    }

}
