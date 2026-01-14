package com.example.meetupflow.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MeetingRoom {

    @Id
    @GeneratedValue
    @Column(name = "meeting_room_id")
    private Long Id;

    private String name;

    private int capacity;

    private int hourlyRate;

    @OneToMany(mappedBy = "meetingRoom")
    private List<Reservation> reservations = new ArrayList<>();
}
