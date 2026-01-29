package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;

import java.time.LocalDateTime;

public record CreatePaymentResponse(
        Long id,
        String transactionId,
        PaymentStatus status,
        PaymentType paymentType,
        double amount,
        String virtualAccount,
        String bankCode,
        String bankName,
        LocalDateTime dueDate,
        String message
) {
    public static CreatePaymentResponse from(Payment payment) {
        return new CreatePaymentResponse(
                payment.getId(),
                payment.getTransactionId(),
                payment.getStatus(),
                payment.getPaymentType(),
                payment.getAmount(),
                payment.getVirtualAccount(),
                payment.getBankCode(),
                payment.getBankName(),
                payment.getDueDate(),
                null
        );
    }
}