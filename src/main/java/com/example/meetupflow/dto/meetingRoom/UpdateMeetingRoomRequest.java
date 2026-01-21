package com.example.meetupflow.dto.meetingRoom;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMeetingRoomRequest {
    private String name;
    private int capacity;
    private int hourlyRate;
}
