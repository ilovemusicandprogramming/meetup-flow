package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;

public record UpdateMeetingRoomResponse(
        Long id,
        String name,
        int capacity,
        int hourlyRate
) {
    public static UpdateMeetingRoomResponse from(MeetingRoom meetingRoom) {
        return new UpdateMeetingRoomResponse(
                meetingRoom.getId(),
                meetingRoom.getName(),
                meetingRoom.getCapacity(),
                meetingRoom.getHourlyRate()
        );
    }
}