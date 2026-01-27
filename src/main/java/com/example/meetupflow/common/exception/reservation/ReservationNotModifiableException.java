package com.example.meetupflow.common.exception.reservation;

import com.example.meetupflow.common.exception.ErrorCode;

public class ReservationNotModifiableException extends ReservationException{
    public ReservationNotModifiableException() {
        super(ErrorCode.NOT_MODIFIABLE_STATUS);
    }
}
