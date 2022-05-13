package com.hr.spring.service;

import com.hr.spring.model.dto.UserDTO;
import com.hr.spring.model.entity.Status;
import com.hr.spring.model.entity.User;
import com.hr.spring.model.dto.request.LoginRequest;
import com.hr.spring.model.mapper.CompanyMapper;
import com.hr.spring.model.mapper.UserMapper;
import com.hr.spring.repository.StatusRepository;
import com.hr.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final CompanyMapper companyMapper;
    private final StatusRepository statusRepository;


    //-------ADMIN----------

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.modelToDto(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setFather_name(userDTO.getFather_name());
        user.setYear_of_birth(userDTO.getYear_of_birth());
        user.setPhone(userDTO.getPhone());
        user.setCompany(companyMapper.dtoToModel(companyService.getCompany(userDTO.getCompanyId())));
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

    //-----MODERATOR----

    public List<UserDTO> getCompanyUsers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Long companyId = user.getCompany().getId();
        List<User> users = userRepository.findAll().stream()
                .filter(user1 -> user1.getCompany().getId().equals(companyId))
                .collect(Collectors.toList());
        showUsers(users);
        return users.stream().map(userMapper::modelToDto).collect(Collectors.toList());
    }

    public List<User> showUsers(List<User> users) {
        users.removeIf(user -> user.getStatuses().removeIf(status -> status.getDescription().equals("DELETE")));
        return users;
    }


    public UserDTO getUser(Long modId, Long id) {
        User companyUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        User user = userRepository.findById(modId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (!user.getCompany().getId().equals(companyUser.getCompany().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        return userMapper.modelToDto(companyUser);
    }

    public UserDTO create(Long modId, UserDTO userDTO) {
        User user = userRepository.findById(modId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        User newUser = userMapper.dtoToModel(userDTO);
        newUser.setCompany(user.getCompany());
        userRepository.save(newUser);
        return userDTO;
    }

    public UserDTO update(Long modId, Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        User modUser = userRepository.findById(modId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Long compId = userRepository.getById(modId).getCompany().getId();
        Long userId = userRepository.getById(id).getCompany().getId();
        if (!compId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setFather_name(userDTO.getFather_name());
        user.setYear_of_birth(userDTO.getYear_of_birth());
        user.setPhone(userDTO.getPhone());
        user.setCompany(modUser.getCompany());
        user.setPosition(userDTO.getPosition());
        user.setExperience(userDTO.getExperience());
        user.setMaritalStatus(userDTO.getMaritalStatus());
        userRepository.save(user);
        return userDTO;
    }

    public ResponseEntity<HttpStatus> delete(Long modId, Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Long compId = userRepository.getById(modId).getCompany().getId();
        Long userId = userRepository.getById(id).getCompany().getId();
        if (!compId.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Status status = new Status("DELETE", user);
        statusRepository.save(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
