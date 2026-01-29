package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;

public record MeetingRoomListResponse(
        Long meetingRoomId,
        String name,
        int capacity,
        int hourlyRate
) {

    public static MeetingRoomListResponse from(MeetingRoom meetingRoom) {
        return new MeetingRoomListResponse(
                meetingRoom.getId(),
                meetingRoom.getName(),
                meetingRoom.getCapacity(),
                meetingRoom.getHourlyRate()
        );
    }
}