package com.hr.spring.controller;

import com.hr.spring.model.dto.UserTrainingDto;
import com.hr.spring.service.UserTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-training")
public class UserTrainingController {
    private final UserTrainingService userTrainingService;

    @GetMapping
    public ResponseEntity<List<UserTrainingDto>> getUT() {
        return new ResponseEntity<>(userTrainingService.getUT(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTrainingDto> getUT(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userTrainingService.getUT(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserTrainingDto> create(@RequestBody UserTrainingDto userTrainingDTO) {
        return new ResponseEntity<>(userTrainingService.create(userTrainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserTrainingDto> update(@PathVariable("id") Integer id,
                                                  @RequestBody UserTrainingDto userTrainingDTO) {
        return new ResponseEntity<>(userTrainingService.updateUT(id, userTrainingDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        return userTrainingService.delete(id);
    }
}
