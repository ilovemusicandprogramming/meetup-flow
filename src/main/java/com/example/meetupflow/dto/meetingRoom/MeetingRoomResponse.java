package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.dto.reservation.ReservationDto;

import java.util.List;

public record MeetingRoomResponse(
        Long id,
        String name,
        int capacity,
        int hourlyRate,
        List<ReservationDto> reservations
) {
    public static MeetingRoomResponse from(MeetingRoom meetingRoom) {
        return new MeetingRoomResponse(
                meetingRoom.getId(),
                meetingRoom.getName(),
                meetingRoom.getCapacity(),
                meetingRoom.getHourlyRate(),
                meetingRoom.getReservations().stream()
                        .map(ReservationDto::from)
                        .toList()
        );
    }
}