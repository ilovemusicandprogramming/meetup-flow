package com.example.meetupflow.dto.reservation;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        Long meetingRoomId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long userId
) {
    public static CreateReservationRequest from(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long userId) {
        return new CreateReservationRequest(meetingRoomId, startTime, endTime, userId);
    }
}