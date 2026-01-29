package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationListResponse(
        Long reservationId,
        Long meetingRoomId,
        String userName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime reservationAt,
        double totalAmount,
        ReservationStatus status
) {
    public ReservationListResponse(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getMeetingRoom().getId(),
                reservation.getUser().getName(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getReservationAt(),
                reservation.getTotalAmount(),
                reservation.getStatus()
        );
    }
}