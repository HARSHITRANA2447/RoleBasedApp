package com.example.rolebasedapp.controller;

import com.example.rolebasedapp.dto.UserDTO;
import com.example.rolebasedapp.service.HRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class HRController {

    private final HRService hrService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = hrService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", hrService.getTotalUserCount());
        stats.put("regularUsers", hrService.getUserCountByRole("USER"));
        stats.put("hrUsers", hrService.getUserCountByRole("HR"));
        stats.put("adminUsers", hrService.getUserCountByRole("ADMIN"));
        return ResponseEntity.ok(stats);
    }
}