package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;

public record CreateReservationResponse(Long id) {
    public static CreateReservationResponse from(Reservation reservation) {
        return new CreateReservationResponse(
                reservation.getId()
        );
    }
}