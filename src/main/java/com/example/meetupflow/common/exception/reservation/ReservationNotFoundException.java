package com.example.meetupflow.common.exception.reservation;

import com.example.meetupflow.common.exception.BusinessException;
import com.example.meetupflow.common.exception.ErrorCode;

public class ReservationNotFoundException extends BusinessException {

    public ReservationNotFoundException(Long reservationId) {
        super(ErrorCode.RESERVATION_NOT_FOUND,
                String.format("예약를 찾을 수 없습니다: reservationId=%d", reservationId)
        );
    }
}
