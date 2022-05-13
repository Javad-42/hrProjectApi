package com.hr.spring.service;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.model.dto.ExamQuestionDTO;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.entity.ExamQuestion;
import com.hr.spring.model.mapper.ExamMapper;
import com.hr.spring.model.mapper.ExamQuestionMapper;
import com.hr.spring.repository.examsRepository.ExamQuestionRepository;
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

    public List<ExamQuestionDTO> getQuestions(Long id) {
        ExamDTO examDTO = examService.getExam(id);
        return examDTO.getExamQuestionList();
    }

    public ExamQuestionDTO getQuestion(Long id, Long id1) {
        List<ExamQuestionDTO> examQuestionList = getQuestions(id);
        for (ExamQuestionDTO examQuestion : examQuestionList) {
            if (examQuestion.getId().equals(id1)) {
                return examQuestion;
            }
        }
        return null;
    }

    public List<ExamQuestionDTO> createQuestions(Long id, List<ExamQuestionDTO> examQuestionList) {
        ExamDTO examDTO = examService.getExam(id);
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

    public ExamQuestionDTO updateQuestions(Long id, Long id1, ExamQuestionDTO examQuestionDTO) {
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

    public ResponseEntity<HttpStatus> deleteQuestion(Long id) {
        if (!examQuestionRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Question not found!");
        examQuestionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
