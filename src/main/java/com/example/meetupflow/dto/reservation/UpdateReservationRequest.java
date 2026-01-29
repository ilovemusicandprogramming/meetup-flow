package com.example.meetupflow.dto.reservation;

import java.time.LocalDateTime;

public record UpdateReservationRequest(
        Long meetingRoomId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public static UpdateReservationRequest from(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime) {
        return new UpdateReservationRequest(meetingRoomId, startTime, endTime);
    }
}