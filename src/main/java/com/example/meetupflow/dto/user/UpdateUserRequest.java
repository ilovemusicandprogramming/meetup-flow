package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;

public record UpdateUserRequest(
        String email,
        Address address
) {
    public static UpdateUserRequest from(String email, Address address) {
        return new UpdateUserRequest(email, address);
    }
}