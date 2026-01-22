package com.example.meetupflow.infrastructure.clients;

import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.infrastructure.PaymentClient;
import org.springframework.stereotype.Component;

@Component
public class SimplePaymentClient implements PaymentClient {
    @Override
    public boolean supports(PaymentType type) {
        return type == PaymentType.SIMPLE;
    }

    @Override
    public PaymentResponse requestPayment(PaymentRequest request) {
        return null;
    }
}
