package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long paymentId,
        Long reservationId,
        PaymentType paymentType,
        PaymentStatus status,
        double amount,
        String transactionId,
        LocalDateTime requestedAt,
        LocalDateTime completedAt,
        String message,
        String virtualAccount,
        LocalDateTime dueDate,
        String bankName
) {
    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getReservation().getId(),
                payment.getPaymentType(),
                payment.getStatus(),
                payment.getAmount(),
                payment.getTransactionId(),
                payment.getRequestedAt(),
                payment.getCompletedAt(),
                null,
                payment.getVirtualAccount(),
                LocalDateTime.now(), // 기존 로직 유지
                payment.getBankName()
        );
    }
}