package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMeetingRoomResponse {
    private Long id;
    private String name;
    private int capacity;
    private int hourlyRate;

    public UpdateMeetingRoomResponse(MeetingRoom meetingRoom) {
        this.id = meetingRoom.getId();
        this.name = meetingRoom.getName();
        this.capacity = meetingRoom.getCapacity();
        this.hourlyRate = meetingRoom.getCapacity();
    }
}
