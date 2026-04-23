package com.example.user.application.dto;

import com.example.user.domain.entities.User;

public record UserProfileResponse(Long id, String username, String email) {
    public UserProfileResponse(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
