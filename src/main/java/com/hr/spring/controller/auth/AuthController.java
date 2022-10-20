package com.hr.spring.controller.auth;

import com.hr.spring.model.dto.request.LoginRequest;
import com.hr.spring.model.dto.request.SignupRequest;
import com.hr.spring.model.entity.User;
import com.hr.spring.repository.UserRepository;
import com.hr.spring.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final Login login;
    private final Register register;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return login.checkLogin(loginRequest);
    }

 /*   @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody SignupRequest signUpRequest) {
        if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("MODERATOR")))
            return registerUser.registerUser(userDetails.getId(), signUpRequest);
        else if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equalsIgnoreCase("ADMIN"))) {
            return registerUser.registerModerator(signUpRequest);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }*/

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        var user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .fatherName(signUpRequest.getFather_name())
                .yearOfBirth(signUpRequest.getYear_of_birth().toLocalDate())
                .phone(signUpRequest.getPhone())
                .position(signUpRequest.getPosition())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
