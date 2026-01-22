package com.example.meetupflow.infrastructure;

import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;

public interface PaymentClient {
    boolean supports(PaymentType type);
    PaymentResponse requestPayment(PaymentRequest request);
}
