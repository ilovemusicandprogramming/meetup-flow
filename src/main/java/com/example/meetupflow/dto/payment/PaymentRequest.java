package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;

public record PaymentRequest(
        String transactionId,
        double amount,
        String userName
) {
    public static PaymentRequest from(Payment payment) {
        return new PaymentRequest(
                payment.getTransactionId(),
                payment.getAmount(),
                null
        );
    }
}