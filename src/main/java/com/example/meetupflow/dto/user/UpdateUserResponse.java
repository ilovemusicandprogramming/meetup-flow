package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateUserResponse {

    private String email;
    private Address address;

    public UpdateUserResponse(User user) {
        this.email = user.getEmail();
        this.address = user.getAddress();
    }

    public static UpdateUserResponse from(User user) {
        return new UpdateUserResponse(user);
    }
}
