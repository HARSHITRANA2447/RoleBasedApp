package com.example.rolebasedapp.service;

import com.example.rolebasedapp.dto.UserDTO;
import com.example.rolebasedapp.model.Role;
import com.example.rolebasedapp.model.User;
import com.example.rolebasedapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }

    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Prevent admin from deleting themselves or other admins
        if (user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("Cannot delete admin users");
        }

        userRepository.deleteById(userId);
    }

    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Prevent updating admin users
        if (user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("Cannot update admin users");
        }

        // Update allowed fields
        if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
            user.setName(userDTO.getName());
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            // Check if email is already taken by another user
            if (userRepository.existsByEmail(userDTO.getEmail()) &&
                    !user.getEmail().equals(userDTO.getEmail())) {
                throw new IllegalArgumentException("Email already in use");
            }
            user.setEmail(userDTO.getEmail());
        }

        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);

        return UserDTO.fromUser(updatedUser);
    }

    public long getTotalUserCount() {
        return userRepository.count();
    }
}