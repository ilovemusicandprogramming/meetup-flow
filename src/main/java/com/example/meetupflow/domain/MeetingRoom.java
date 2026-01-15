package com.example.meetupflow.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
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

    public static MeetingRoom createMeetingRoom(String name, int capacity, int hourlyRate) {
        MeetingRoom meetingRoom = new MeetingRoom();

        meetingRoom.name = name;
        meetingRoom.capacity = capacity;
        meetingRoom.hourlyRate = hourlyRate;

        return meetingRoom;
    }
}
