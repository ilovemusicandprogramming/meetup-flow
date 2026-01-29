package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.User;

public record CreateUserResponse(Long id) {
    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(user.getId());
    }
}