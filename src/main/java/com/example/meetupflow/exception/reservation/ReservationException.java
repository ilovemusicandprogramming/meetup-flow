package com.example.meetupflow.exception.reservation;

import com.example.meetupflow.exception.ErrorCode;

public class ReservationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ReservationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
