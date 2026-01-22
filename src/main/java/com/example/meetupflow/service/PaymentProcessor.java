package com.example.meetupflow.service;

import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.infrastructure.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentProcessor {

    private final List<PaymentClient> paymentClients;

    public PaymentResponse execute(PaymentType type, PaymentRequest request) {
        return paymentClients.stream()
                .filter(client -> client.supports(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 결제 방식입니다: " + type))
                .requestPayment(request);
    }
}
