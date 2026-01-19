package com.example.meetupflow.domain;

import com.example.meetupflow.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MeetingRoom extends BaseEntity {

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

    public void updateProfile(String name, int capacity, int hourlyRate) {
        if(!this.reservations.isEmpty()){
            throw new IllegalStateException("이미 예약된 건이 있는 회의실은 정보를 수정할 수 없습니다.");
        }

        this.name = name;
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
    }
}
