package com.example.meetupflow.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserResponse {
    private Long id;

    public CreateUserResponse(Long id) {
        this.id = id;
    }
}
