package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.status.PaymentType;

public record CreatePaymentRequest(
        Long reservationId,
        PaymentType paymentType
) {
}