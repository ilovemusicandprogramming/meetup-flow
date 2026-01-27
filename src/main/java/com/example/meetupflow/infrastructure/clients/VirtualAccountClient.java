package com.example.meetupflow.infrastructure.clients;

import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.infrastructure.PaymentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class VirtualAccountClient implements PaymentClient {
    @Override
    public boolean supports(PaymentType type) {
        return type == PaymentType.VIRTUAL_ACCOUNT;
    }

    @Override
    public PaymentResponse requestPayment(PaymentRequest request) {
        validateRequest(request);

        String accountNumber = generateAccountNumber();

        log.info("[VirtualAccount] 가상계좌 발급 - 거래ID: {}, 계좌: {}",
                request.getTransactionId(), accountNumber);

        return PaymentResponse.builder()
                .status(PaymentStatus.PENDING)
                .transactionId(request.getTransactionId())
                .virtualAccount(accountNumber)
                .dueDate(LocalDateTime.now().plusDays(3))
                .message("가상계좌로 입금해주세요")
                .build();
    }

    private void validateRequest(PaymentRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("결제 금액은 0보다 커야 합니다");
        }
    }

    private String generateAccountNumber() {
        return "110-" + System.currentTimeMillis() % 1000000;
    }
}
