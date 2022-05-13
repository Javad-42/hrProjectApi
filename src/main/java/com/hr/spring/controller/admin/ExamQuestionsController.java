package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.ExamQuestionDTO;
import com.hr.spring.service.ExamQuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/exams")
public class ExamQuestionsController {
    private final ExamQuestionsService examQuestionsService;

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<ExamQuestionDTO>> getQ(@PathVariable("id") Long id) {
        return new ResponseEntity<>(examQuestionsService.getQuestions(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/questions/{id1}")
    public ResponseEntity<ExamQuestionDTO> getQuestion(@PathVariable("id") Long id, @PathVariable("id1") Long id1) {
        ExamQuestionDTO examQuestion = examQuestionsService.getQuestion(id, id1);
        if (examQuestion == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok().body(examQuestion);
    }

    @PostMapping("/{id}/questions")
    public ResponseEntity<List<ExamQuestionDTO>> createQuestions(@PathVariable("id") Long id,
                                                                 @RequestBody List<ExamQuestionDTO> examQuestion) {
        return new ResponseEntity<>(examQuestionsService.createQuestions(id, examQuestion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/questions/{id1}")
    public ResponseEntity<ExamQuestionDTO> updateQuestions(@PathVariable("id") Long id,
                                                           @PathVariable("id1") Long id1,
                                                           @RequestBody ExamQuestionDTO examQuestionDTO) {
        ExamQuestionDTO examQuestion1 = examQuestionsService.updateQuestions(id, id1, examQuestionDTO);
        return new ResponseEntity<>(examQuestion1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/questions/{id1}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id1") Long id1) {
        return examQuestionsService.deleteQuestion(id1);
    }
}
