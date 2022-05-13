package com.hr.spring.controller.admin;

import com.hr.spring.model.dto.UserTrainingDTO;
import com.hr.spring.service.UserTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/user-training")
public class UserTrainingController {
    private final UserTrainingService userTrainingService;

    @GetMapping
    public ResponseEntity<List<UserTrainingDTO>> getUT() {
        return new ResponseEntity<>(userTrainingService.getUT(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTrainingDTO> getUT(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userTrainingService.getUT(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserTrainingDTO> create(@RequestBody UserTrainingDTO userTrainingDTO) {
        return new ResponseEntity<>(userTrainingService.create(userTrainingDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserTrainingDTO> update(@PathVariable("id") Long id,
                                                  @RequestBody UserTrainingDTO userTrainingDTO) {
        return new ResponseEntity<>(userTrainingService.updateUT(id, userTrainingDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return userTrainingService.delete(id);
    }
}
