package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {
    private String email;
    private Address address;
}
