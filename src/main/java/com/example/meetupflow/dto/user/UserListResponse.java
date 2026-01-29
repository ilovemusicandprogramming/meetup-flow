package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;

public record UserListResponse(
        String name,
        String email,
        Address address
) {
    public UserListResponse (User user) {
        this(
                user.getName(),
                user.getEmail(),
                user.getAddress()
        );
    }
}