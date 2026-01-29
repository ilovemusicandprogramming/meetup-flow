package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
        @NotEmpty
        String name,
        String email,
        Address address
) {
    public static CreateUserRequest from(String name, String email, Address address) {
        return new CreateUserRequest(name, email, address);
    }
}