package com.hr.spring.controller.moderator;

import com.hr.spring.model.dto.CompanyTrainingDTO;
import com.hr.spring.model.dto.TrainingDTO;
import com.hr.spring.model.dto.UserDTO;
import com.hr.spring.security.services.UserDetailsImpl;
import com.hr.spring.service.CompanyTrainingService;
import com.hr.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/moderator/company-trainings")
public class CompanyTrainingController {
    private final CompanyTrainingService companyTrainingService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<TrainingDTO>> getTrainings(@AuthenticationPrincipal UserDetailsImpl user) {
        UserDTO userDTO =userService.getUser(user.getId());
        return new ResponseEntity<>(companyTrainingService.getTr(userDTO.getCompanyId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyTrainingDTO> getCT(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUser(user.getId());
        return new ResponseEntity<>(companyTrainingService.getCT(userDTO.getCompanyId(), id), HttpStatus.OK);
    }
}
