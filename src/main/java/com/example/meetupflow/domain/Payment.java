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
import java.util.UUID;

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
    @Column(unique = true, nullable = false)
    private String transactionId;           // 거래 ID
    private LocalDateTime requestedAt;      // 결제 요청 시각
    private LocalDateTime completedAt;      // 결제 완료 시각
    private LocalDateTime updatedAt;
    private String errorMessage;
    private String virtualAccount;
    private String bankCode;
    private String bankName;
    private LocalDateTime dueDate;
    @Version
    private Long version;

    public static Payment createPayment(Reservation reservation, PaymentType paymentType, double totalAmount) {
        Payment payment = new Payment();

        payment.reservation = reservation;
        payment.paymentType = paymentType;
        payment.transactionId = generateTransactionId();
        payment.amount = reservation.getTotalAmount();
        payment.status = PaymentStatus.PENDING;
        payment.requestedAt = LocalDateTime.now();

        return payment;
    }

    /**
     * 결제 처리 완료
     * - 카드/간편결제: 즉시 COMPLETED
     * - 가상계좌: PENDING (입금 대기)
     */
    public void complete(PaymentResponse paymentResponse) {
        // 1. 상태 업데이트 (응답에서 받은 상태 그대로)
        this.status = paymentResponse.getStatus();

        // 2. 결제 수단별 처리
        if (this.paymentType == PaymentType.VIRTUAL_ACCOUNT) {
            // 가상계좌: 정보 저장 (입금 대기)
            this.virtualAccount = paymentResponse.getVirtualAccount();
            this.bankName = paymentResponse.getBankName();
            this.dueDate = paymentResponse.getDueDate();
            // 예약은 PENDING 상태 유지

        } else {
            // 카드/간편결제: 즉시 완료
            this.completedAt = LocalDateTime.now();
            this.reservation.confirm();  // 예약 확정
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void fail(String errorMessage) {
        this.status = PaymentStatus.FAILED;
        this.errorMessage = errorMessage;
        this.updatedAt = LocalDateTime.now();
        this.reservation.changeStatusToCancel();
    }

    /**
     * 가상계좌입금완료
     */
    public void updateStatusFromWebhook(PaymentStatus newStatus) {
        validateStatusTransition(newStatus);

        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();

        // 입금 완료 시 예약 확정
        if (newStatus == PaymentStatus.COMPLETED) {
            this.completedAt = LocalDateTime.now();
            this.reservation.confirm();
        }
    }

    private void validateStatusTransition(PaymentStatus newStatus) {
        if (this.status == PaymentStatus.COMPLETED) {
            throw new IllegalStateException("이미 완료된 결제는 변경할 수 없습니다");
        }
    }

    private static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
