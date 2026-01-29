package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationDto(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime reservationAt,
        ReservationStatus status
) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getReservationAt(),
                reservation.getStatus()
        );
    }
}