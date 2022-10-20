package com.hr.spring.controller.auth;

import com.hr.spring.model.ERole;
import com.hr.spring.model.dto.request.SignupRequest;
import com.hr.spring.model.dto.response.MessageResponse;
import com.hr.spring.model.entity.Company;
import com.hr.spring.model.entity.Role;
import com.hr.spring.model.entity.User;
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

        var persistUser = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .fatherName(signUpRequest.getFather_name())
                .yearOfBirth(signUpRequest.getYear_of_birth().toLocalDate())
                .phone(signUpRequest.getPhone())
                .position(signUpRequest.getPosition())
                .company(company)
                .build();

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        persistUser.setRoles(roles);
        userRepository.save(persistUser);
        return ResponseEntity.ok(userMapper.modelToDto(persistUser));
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
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        var persistUser = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .surname(signUpRequest.getSurname())
                .fatherName(signUpRequest.getFather_name())
                .yearOfBirth(signUpRequest.getYear_of_birth().toLocalDate())
                .phone(signUpRequest.getPhone())
                .position(signUpRequest.getPosition())
                .company(company)
                .roles(roles)
                .build();

        userRepository.save(persistUser);
        return ResponseEntity.ok(userMapper.modelToDto(persistUser));
    }
}
