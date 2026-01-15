package com.example.meetupflow.dto.meetingRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
