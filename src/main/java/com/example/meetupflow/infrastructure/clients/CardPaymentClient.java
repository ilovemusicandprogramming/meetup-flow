package com.example.meetupflow.infrastructure.clients;

import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.infrastructure.PaymentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CardPaymentClient implements PaymentClient {

    @Override
    public boolean supports(PaymentType type) {
        return type == PaymentType.CARD;
    }

    @Override
    public PaymentResponse requestPayment(PaymentRequest request) {
        log.info("[CardPaymentClient] 결제 요청 시작 - 거래ID: {}, 금액: {}",
                request.getTransactionId(), request.getAmount());

        return PaymentResponse.builder()
                .status(PaymentStatus.COMPLETED)
                .transactionId(request.getTransactionId())
//                .paymentKey("MOCK_KEY_" + UUID.randomUUID().toString().substring(0, 8)) // 가짜 승인 키
                .message("카드 결제가 정상 처리되었습니다.")
                .build();
    }
}
