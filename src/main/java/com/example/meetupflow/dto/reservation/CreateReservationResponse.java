package com.example.meetupflow.dto.reservation;

public record CreateReservationResponse(Long id) {
    public static CreateReservationResponse from(Long id) {
        return new CreateReservationResponse(id);
    }
}