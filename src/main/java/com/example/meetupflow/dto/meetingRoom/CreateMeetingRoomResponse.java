package com.example.meetupflow.dto.meetingRoom;

public record CreateMeetingRoomResponse(Long id) {

    public static CreateMeetingRoomResponse from(Long id) {
        return new CreateMeetingRoomResponse(id);
    }
}