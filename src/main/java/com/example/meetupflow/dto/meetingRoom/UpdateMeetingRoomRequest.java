package com.example.meetupflow.dto.meetingRoom;

public record UpdateMeetingRoomRequest(
        String name,
        int capacity,
        int hourlyRate
) {

}