package com.hr.spring.service;

import com.hr.spring.model.dto.ExamDto;
import com.hr.spring.model.dto.ExamQuestionDto;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.entity.ExamQuestion;
import com.hr.spring.model.mapper.ExamMapper;
import com.hr.spring.model.mapper.ExamQuestionMapper;

import com.hr.spring.repository.ExamQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamQuestionsService {
    private final ExamService examService;
    private final ExamQuestionRepository examQuestionRepository;
    private final ExamMapper examMapper;
    private final ExamQuestionMapper examQuestionMapper;

    public List<ExamQuestionDto> getQuestions(Integer id) {
        return null;
    }

    public ExamQuestionDto getQuestion(Integer id, Long id1) {
        List<ExamQuestionDto> examQuestionList = getQuestions(id);
        for (ExamQuestionDto examQuestion : examQuestionList) {
            if (examQuestion.getId().equals(id1)) {
                return examQuestion;
            }
        }
        return null;
    }

    public List<ExamQuestionDto> createQuestions(Integer id, List<ExamQuestionDto> examQuestionList) {
        ExamDto examDTO = examService.getExam(id);
        Exam exam = examMapper.dtoToModel(examDTO);

        List<ExamQuestion> examQuestions = examQuestionList
                .stream()
                .map(examQuestionMapper::dtoToModel)
                .collect(Collectors.toList());

        for (ExamQuestion question : examQuestions) {
            question.setExam(exam);
        }
        for (ExamQuestion examQuestion : examQuestions) {
            examQuestion.getQuestionAnswers().forEach(x -> x.setExamQuestion(examQuestion));
        }
        examQuestionRepository.saveAll(examQuestions);
        return examQuestionList;
    }

    public ExamQuestionDto updateQuestions(Integer id, Integer id1, ExamQuestionDto examQuestionDTO) {
        ExamQuestion examQuestion = examQuestionRepository.findById(id1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ExamQuestion examQuestion1 = examQuestionMapper.dtoToModel(examQuestionDTO);
        for (int i = 0; i < examQuestion1.getQuestionAnswers().size(); i++) {
            examQuestion.getQuestionAnswers().get(i).setAnswer(examQuestion1.getQuestionAnswers().get(i).getAnswer());
            examQuestion.getQuestionAnswers().get(i).setScore(examQuestion1.getQuestionAnswers().get(i).getScore());
            examQuestion.getQuestionAnswers().get(i).setExamQuestion(examQuestion);
        }
        examQuestion.setQuestion(examQuestion1.getQuestion());
        examQuestionRepository.save(examQuestion);
        return examQuestionMapper.modelToDto(examQuestion1);
    }

    public ResponseEntity<HttpStatus> deleteQuestion(Integer id) {
        if (!examQuestionRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Question not found!");
        examQuestionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
