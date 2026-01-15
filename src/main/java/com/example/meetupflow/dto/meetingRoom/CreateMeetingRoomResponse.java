package com.example.meetupflow.dto.meetingRoom;

import lombok.Getter;

@Getter
public class CreateMeetingRoomResponse {

    private Long Id;

    public CreateMeetingRoomResponse(Long id) {
        Id = id;
    }
}
