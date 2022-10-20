package com.hr.spring.service;

import com.hr.spring.model.dto.ExamDto;
import com.hr.spring.model.entity.Exam;
import com.hr.spring.model.mapper.ExamMapper;

import com.hr.spring.repository.ExamRepository;
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

    public List<ExamDto> getExams() {
        return examRepository.findAll()
                .stream()
                .map(examMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public ExamDto getExam(Integer id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
        return examMapper.modelToDto(exam);
    }

    public ExamDto createExam(ExamDto examDTO) {
        Exam exam = examMapper.dtoToModel(examDTO);
        examRepository.save(exam);
        return examDTO;
    }

    public ExamDto updateExam(Integer id, ExamDto examDTO) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
        exam.setName(examDTO.getName());
        examRepository.save(exam);
        return examDTO;
    }

    public ResponseEntity<HttpStatus> deleteByExam(Integer id) {
        if (!examRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Exam not found");
        }
        examRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
