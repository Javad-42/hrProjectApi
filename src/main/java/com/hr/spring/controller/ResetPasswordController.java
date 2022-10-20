package com.hr.spring.controller;


import com.hr.spring.model.dto.request.LoginRequest;
import com.hr.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reset")
public class ResetPasswordController {
    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> resetPass(@PathVariable("id") Long id,
                                                @RequestBody LoginRequest loginRequest) {
        return userService.resetPass(id, loginRequest);
    }
}
