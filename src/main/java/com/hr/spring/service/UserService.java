package com.hr.spring.service;

import com.hr.spring.model.dto.UserDto;
import com.hr.spring.model.dto.request.LoginRequest;
import com.hr.spring.model.entity.Status;
import com.hr.spring.model.entity.User;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.repository.StatusRepository;
import com.hr.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final StatusRepository statusRepository;



    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.modelToDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setFatherName(userDTO.getFatherName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setPhone(userDTO.getPhone());
        user.setPosition(userDTO.getPosition());
        user.setExperience(userDTO.getExperience());
        user.setMaritalStatus(userDTO.getMaritalStatus());
        userRepository.save(user);
        return userDTO;
    }

    public ResponseEntity<HttpStatus> deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> resetPass(Long id, LoginRequest loginRequest) {

        if (!userRepository.existsByUsername(loginRequest.getUsername()) || !userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = userRepository.getById(id);
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
