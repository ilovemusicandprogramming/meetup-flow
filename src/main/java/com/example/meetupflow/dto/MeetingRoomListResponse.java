package com.example.meetupflow.dto;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class MeetingRoomListResponse {

    private Long meetingRoomId;
    private String name;
    private int capacity;
    private int hourlyRate;

    public MeetingRoomListResponse(MeetingRoom meetingRoom) {
        this.meetingRoomId = meetingRoom.getId();
        this.name = meetingRoom.getName();
        this.capacity = meetingRoom.getCapacity();
        this.hourlyRate = meetingRoom.getHourlyRate();
    }
}
