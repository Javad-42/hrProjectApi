package com.hr.spring.controller;

import com.hr.spring.model.dto.ExamQuestionDto;
import com.hr.spring.service.ExamQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exams")
public class ExamQuestionsController {
    private final ExamQuestionsService examQuestionsService;

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<ExamQuestionDto>> getQ(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(examQuestionsService.getQuestions(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/questions/{id1}")
    public ResponseEntity<ExamQuestionDto> getQuestion(@PathVariable("id") Integer id, @PathVariable("id1") Long id1) {
        ExamQuestionDto examQuestion = examQuestionsService.getQuestion(id, id1);
        if (examQuestion == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok().body(examQuestion);
    }

    @PostMapping("/{id}/questions")
    public ResponseEntity<List<ExamQuestionDto>> createQuestions(@PathVariable("id") Integer id,
                                                                 @RequestBody List<ExamQuestionDto> examQuestion) {
        return new ResponseEntity<>(examQuestionsService.createQuestions(id, examQuestion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/questions/{id1}")
    public ResponseEntity<ExamQuestionDto> updateQuestions(@PathVariable("id") Integer id,
                                                           @PathVariable("id1") Integer id1,
                                                           @RequestBody ExamQuestionDto examQuestionDTO) {
        ExamQuestionDto examQuestion1 = examQuestionsService.updateQuestions(id, id1, examQuestionDTO);
        return new ResponseEntity<>(examQuestion1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/questions/{id1}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id1") Integer id1) {
        return examQuestionsService.deleteQuestion(id1);
    }
}
