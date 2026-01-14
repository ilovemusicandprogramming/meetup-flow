package com.example.meetupflow.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    public static User createUser(String name, String email, Address address) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }

        User user = new User();
        user.name = name;
        user.email = email;
        user.address = address;

        return user;
    }

}
