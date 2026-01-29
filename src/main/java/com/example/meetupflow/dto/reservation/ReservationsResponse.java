package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationsResponse(
        Long id,
        String meetingRoomName,
        String userName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime reservationAt,
        double totalAmount,
        ReservationStatus status
) {
    public static ReservationsResponse from(Reservation reservation) {
        return new ReservationsResponse(
                reservation.getId(),
                reservation.getMeetingRoom().getName(),
                reservation.getUser().getName(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getReservationAt(),
                reservation.getTotalAmount(),
                reservation.getStatus()
        );
    }
}