package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequest {
    private String transactionId;    // 우리 쪽 거래 식별자 (TXN_...)
    private double amount;       // 결제 금액
    private String userName;     // 고객 이름

    public static PaymentRequest from(Payment payment) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.transactionId = payment.getTransactionId();
        paymentRequest.amount = payment.getAmount();
        return paymentRequest;
    }
}
