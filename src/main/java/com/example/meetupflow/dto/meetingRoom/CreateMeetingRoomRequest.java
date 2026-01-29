package com.example.meetupflow.dto.meetingRoom;

public record CreateMeetingRoomRequest(
        Long meetingRoomId,
        String name,
        int capacity,
        int hourlyRate
) {
}