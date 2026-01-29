package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;

import java.time.LocalDateTime;

public record UpdateReservationResponse(
        Long meetingRoomId,
        String userName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ReservationStatus status
) {
    public static UpdateReservationResponse from(Reservation reservation) {
        return new UpdateReservationResponse(
                reservation.getMeetingRoom().getId(),
                reservation.getUser().getName(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStatus()
        );
    }
}