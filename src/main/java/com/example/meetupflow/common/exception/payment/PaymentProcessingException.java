package com.example.meetupflow.common.exception.payment;

import com.example.meetupflow.common.exception.BusinessException;
import com.example.meetupflow.common.exception.ErrorCode;

public class PaymentProcessingException extends BusinessException {

    public PaymentProcessingException(String message) {
        super(ErrorCode.PAYMENT_PROCESSING_FAILED, message);
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(ErrorCode.PAYMENT_PROCESSING_FAILED, message, cause);
    }

    public PaymentProcessingException() {
        super(ErrorCode.PAYMENT_PROCESSING_FAILED);
    }
}
