package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResponse {
    private String name;
    private String email;
    private Address address;

    public UserListResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }
}
