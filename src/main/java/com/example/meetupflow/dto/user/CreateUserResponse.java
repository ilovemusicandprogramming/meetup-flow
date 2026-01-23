package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserResponse {
    private Long id;

    public CreateUserResponse(User user) {
        this.id = user.getId();
    }

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(user);
    }
}
