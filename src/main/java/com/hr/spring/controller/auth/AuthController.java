package com.hr.spring.controller.auth;

import javax.validation.Valid;

import com.hr.spring.security.jwt.JwtUtils;
import com.hr.spring.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.spring.model.dto.request.LoginRequest;
import com.hr.spring.model.dto.request.SignupRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final Login login;
    private final Register registerUser;
    private final JwtUtils jwtUtils;

    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return login.checkLogin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody SignupRequest signUpRequest) {
        if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("MODERATOR")))
            return registerUser.registerUser(userDetails.getId(), signUpRequest);
        else if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("ADMIN"))) {
            return registerUser.registerModerator(signUpRequest);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
