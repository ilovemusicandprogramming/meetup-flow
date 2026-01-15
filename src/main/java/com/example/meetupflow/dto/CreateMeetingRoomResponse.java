package com.example.meetupflow.dto;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateMeetingRoomResponse {

    private Long Id;

    public CreateMeetingRoomResponse(Long id) {
        Id = id;
    }
}
