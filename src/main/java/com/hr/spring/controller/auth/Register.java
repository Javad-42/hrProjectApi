package com.hr.spring.controller.auth;

import com.hr.spring.model.ERole;
import com.hr.spring.model.entity.Company;
import com.hr.spring.model.entity.Role;
import com.hr.spring.model.entity.User;
import com.hr.spring.model.dto.request.SignupRequest;
import com.hr.spring.model.dto.response.MessageResponse;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.repository.RoleRepository;
import com.hr.spring.repository.UserRepository;
import com.hr.spring.service.CompanyService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Register {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    public ResponseEntity<?> registerUser(Long id, SignupRequest signUpRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Company company = user.getCompany();
        User registerUser = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getFather_name(),
                signUpRequest.getYear_of_birth(),
                signUpRequest.getPhone(),
                signUpRequest.getPosition(),
                company
        );
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        registerUser.setRoles(roles);
        userRepository.save(registerUser);
        return ResponseEntity.ok(userMapper.modelToDto(registerUser));
    }

    public ResponseEntity<?> registerModerator(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Company company = companyMapper.dtoToModel(companyService.getCompany(signUpRequest.getCompanyId()));

        User registerMod = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getFather_name(),
                signUpRequest.getYear_of_birth(),
                signUpRequest.getPhone(),
                signUpRequest.getPosition(),
                company
        );
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        registerMod.setRoles(roles);
        userRepository.save(registerMod);
        return ResponseEntity.ok(userMapper.modelToDto(registerMod));
    }
}
