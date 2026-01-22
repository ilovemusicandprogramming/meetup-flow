package com.example.meetupflow.domain;

import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id @GeneratedValue @Column(name = "payment_id")
    private Long id;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "reservation_id", unique = true)
    private Reservation reservation;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private double amount;
    private String paymentKey;              // 결제사에서 발급한 키
    private String transactionId;           // 거래 ID
    private LocalDateTime requestedAt;      // 결제 요청 시각
    private LocalDateTime completedAt;      // 결제 완료 시각
    private LocalDateTime updatedAt;
    private String errorMessage;

    public static Payment createPayment(Reservation reservation, PaymentType paymentType, double totalAmount, String transactionId) {
        Payment payment = new Payment();

        payment.reservation = reservation;
        payment.paymentType = paymentType;
        payment.transactionId = transactionId;
        payment.amount = reservation.getTotalAmount();
        payment.status = PaymentStatus.PENDING;
        payment.requestedAt = LocalDateTime.now();

        return payment;
    }

    public void complete(PaymentResponse paymentResponse) {
        this.status = PaymentStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.reservation.confirm();
    }

    public void fail(String errorMessage) {
        this.status = PaymentStatus.FAILED;
        this.errorMessage = errorMessage;
        this.updatedAt = LocalDateTime.now();
        this.reservation.changeStatusToCancel();
    }
}
