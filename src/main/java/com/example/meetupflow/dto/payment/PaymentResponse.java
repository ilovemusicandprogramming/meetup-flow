package com.example.meetupflow.dto.payment;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private Long paymentId;
    private Long reservationId;
    private PaymentType paymentType;
    private PaymentStatus status;
    private double amount;
    private String transactionId;           // 거래 ID
    private LocalDateTime requestedAt;      // 결제 요청 시각
    private LocalDateTime completedAt;      // 결제 완료 시각
    private String message;
    private String virtualAccount;
    private LocalDateTime dueDate;
    private String bankName;

    public PaymentResponse(Payment payment) {
        paymentId = payment.getId();
        reservationId = payment.getReservation().getId();
        paymentType = payment.getPaymentType();
        status = payment.getStatus();
        amount = payment.getAmount();
        transactionId = payment.getTransactionId();
        requestedAt = payment.getRequestedAt();
        completedAt = payment.getCompletedAt();
        virtualAccount = payment.getVirtualAccount();
        dueDate = LocalDateTime.now();
    }

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment);
    }

    public boolean isSuccess(){
        return "CONFIRMED".equals(this.status);
    }
}
