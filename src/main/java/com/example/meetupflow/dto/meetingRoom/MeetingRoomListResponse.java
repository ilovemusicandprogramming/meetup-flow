package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MeetingRoomListResponse {

    private Long meetingRoomId;
    private String name;
    private int capacity;
    private int hourlyRate;

    public MeetingRoomListResponse(MeetingRoom meetingRoom) {
        this.meetingRoomId = meetingRoom.getId();
        this.name = meetingRoom.getName();
        this.capacity = meetingRoom.getCapacity();
        this.hourlyRate = meetingRoom.getHourlyRate();
    }
}
