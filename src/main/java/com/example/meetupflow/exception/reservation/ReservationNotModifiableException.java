package com.example.meetupflow.exception.reservation;

import com.example.meetupflow.exception.ErrorCode;

public class ReservationNotModifiableException extends ReservationException{
    public ReservationNotModifiableException() {
        super(ErrorCode.NOT_MODIFIABLE_STATUS);
    }
}
