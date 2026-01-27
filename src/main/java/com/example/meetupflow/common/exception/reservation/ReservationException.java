package com.example.meetupflow.common.exception.reservation;

import com.example.meetupflow.common.exception.ErrorCode;

public class ReservationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ReservationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
