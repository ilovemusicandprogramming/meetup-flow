package com.example.meetupflow.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
}
