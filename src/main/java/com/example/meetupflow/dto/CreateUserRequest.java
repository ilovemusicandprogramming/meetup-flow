package com.example.meetupflow.dto;

import com.example.meetupflow.domain.Address;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

    @NotEmpty
    private String name;
    private String email;
    private Address address;
}
