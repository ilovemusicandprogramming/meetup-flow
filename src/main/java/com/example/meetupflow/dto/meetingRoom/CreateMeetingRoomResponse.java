package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;

public record CreateMeetingRoomResponse(Long id) {

    public static CreateMeetingRoomResponse from(MeetingRoom meetingRoom) {
        return new CreateMeetingRoomResponse(id);
    }
}