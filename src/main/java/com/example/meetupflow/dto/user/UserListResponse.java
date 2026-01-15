package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResponse {
    private String name;
    private String email;
    private Address address;

    public UserListResponse(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
