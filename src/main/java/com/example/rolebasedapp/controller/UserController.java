package com.example.rolebasedapp.controller;

import com.example.rolebasedapp.dto.UserDTO;
import com.example.rolebasedapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getCurrentUserProfile() {
        UserDTO userDTO = userService.getCurrentUserProfile();
        return ResponseEntity.ok(userDTO);
    }
}