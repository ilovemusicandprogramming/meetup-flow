package com.example.meetupflow.common.exception.payment;

import com.example.meetupflow.common.exception.BusinessException;
import com.example.meetupflow.common.exception.ErrorCode;

public class PaymentNotFoundException extends BusinessException {

    public PaymentNotFoundException(Long paymentId) {
        super(
                ErrorCode.PAYMENT_NOT_FOUND,
                String.format("결제를 찾을 수 없습니다: paymentId=%d", paymentId)
        );
    }

    public PaymentNotFoundException(String transactionId) {
        super(
                ErrorCode.PAYMENT_NOT_FOUND,
                String.format("결제를 찾을 수 없습니다: transactionId=%s", transactionId)
        );
    }
}
