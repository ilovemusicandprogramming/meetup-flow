package com.example.meetupflow.dto.meetingRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMeetingRoomRequest {
    private String name;
    private int capacity;
    private int hourlyRate;
}
