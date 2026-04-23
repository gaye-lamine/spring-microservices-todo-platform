package com.example.user.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserCommand(
    @JsonProperty("email") String email,
    @JsonProperty("password") String password
) {}