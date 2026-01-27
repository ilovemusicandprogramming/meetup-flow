package com.example.meetupflow.common.exception.payment;

import com.example.meetupflow.common.exception.BusinessException;
import com.example.meetupflow.common.exception.ErrorCode;

public class DuplicatePaymentException extends BusinessException {

    public DuplicatePaymentException(Long reservationId) {
        super(ErrorCode.PAYMENT_NOT_FOUND,
                String.format("이미 결제가 진행 중 입니다: reservationId=%d ", reservationId)
        );
    }
}
