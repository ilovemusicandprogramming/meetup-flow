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
public class SimplePaymentClient implements PaymentClient {
    @Override
    public boolean supports(PaymentType type) {
        return type == PaymentType.SIMPLE;
    }

    @Override
    public PaymentResponse requestPayment(PaymentRequest request) {
        validateRequest(request);
        try{
            log.info("[SimplePaymentClient] 결제 요청 시작 - 거래ID: {}, 금액: {}",
                    request.getTransactionId(), request.getAmount());

            return PaymentResponse.builder()
                    .status(PaymentStatus.COMPLETED)
                    .transactionId(request.getTransactionId())
                    .message("간편 결제가 정상 처리되었습니다")
                    .build();
        } catch (Exception e) {
            log.error("결제 실패", e);
            return PaymentResponse.builder()
                    .status(PaymentStatus.FAILED)
                    .transactionId(request.getTransactionId())
                    .message(e.getMessage())
                    .build();
        }

    }

    private void validateRequest(PaymentRequest request) {
        if(request.getAmount() <= 0){
            throw new IllegalArgumentException("결제 금액은 0보다 커야 합니다");
        }
    }
}
