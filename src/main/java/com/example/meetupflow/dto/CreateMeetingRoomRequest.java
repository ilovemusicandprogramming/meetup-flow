package com.example.meetupflow.dto;

import com.example.meetupflow.domain.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateMeetingRoomRequest {

    private Long meetingRoomId;
    private String name;
    private int capacity;
    private int hourlyRate;

    public CreateMeetingRoomRequest(Long meetingRoomId, String name, int capacity, int hourlyRate) {
        this.meetingRoomId = meetingRoomId;
        this.name = name;
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
    }
}
