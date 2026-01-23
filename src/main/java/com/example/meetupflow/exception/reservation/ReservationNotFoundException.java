package com.example.meetupflow.exception.reservation;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super("해당 예약을 찾을 수 없습니다. id=" + id);
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
