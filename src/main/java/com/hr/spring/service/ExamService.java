package com.hr.spring.service;

import com.hr.spring.model.dto.ExamDTO;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.mapper.ExamMapper;
import com.hr.spring.repository.examsRepository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public List<ExamDTO> getExams() {
        return examRepository.findAll()
                .stream()
                .map(examMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public ExamDTO getExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
        return examMapper.modelToDto(exam);
    }

    public ExamDTO createExam(ExamDTO examDTO) {
        Exam exam = examMapper.dtoToModel(examDTO);
        examRepository.save(exam);
        return examDTO;
    }

    public ExamDTO updateExam(Long id, ExamDTO examDTO) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
        exam.setName(examDTO.getName());
        examRepository.save(exam);
        return examDTO;
    }

    public ResponseEntity<HttpStatus> deleteByExam(Long id) {
        if (!examRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Exam not found");
        }
        examRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
