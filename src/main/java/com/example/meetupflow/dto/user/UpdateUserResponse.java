package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;

public record UpdateUserResponse(
        String email,
        Address address
) {
    public static UpdateUserResponse from(User user) {
        return new UpdateUserResponse(
                user.getEmail(),
                user.getAddress()
        );
    }
}