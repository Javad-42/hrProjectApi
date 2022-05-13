package com.hr.spring.controller.moderator;

import com.hr.spring.model.dto.UserDTO;
import com.hr.spring.security.services.UserDetailsImpl;
import com.hr.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/moderator/users")
public class MUserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(userService.getCompanyUsers(userDetails.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(userDetails.getId(), id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDetails.getId(), userDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDetails.getId(), id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        return userService.delete(userDetails.getId(), id);
    }
}
