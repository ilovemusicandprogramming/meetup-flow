package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreatePaymentResponse {
    private Long id;
    private String transactionId;
    private PaymentStatus status;
    private PaymentType paymentType;
    private double amount;
    private String virtualAccount;
    private String bankCode;
    private String bankName;
    private LocalDateTime dueDate;
    private String message;

    public CreatePaymentResponse(Payment payment) {
        this.id = payment.getId();
        this.transactionId = payment.getTransactionId();
        this.status = payment.getStatus();
        this.paymentType = payment.getPaymentType();
        this.amount = payment.getAmount();
        this.virtualAccount = payment.getVirtualAccount();
        this.bankCode = payment.getBankCode();
        this.bankName = payment.getBankName();
        this.dueDate = payment.getDueDate();
    }

    public static CreatePaymentResponse from(Payment payment) {
        return new CreatePaymentResponse(payment);
    }
}
