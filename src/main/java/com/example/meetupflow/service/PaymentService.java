package com.example.meetupflow.service;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.repository.PaymentRepository;
import com.example.meetupflow.repository.ReservationRepository;
import com.querydsl.core.dml.DMLClause;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentProcessor paymentProcessor;

    /**
     * 결제단건조회
     */
    @Transactional(readOnly = true)
    public Payment findOne(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
    }

    /**
     * 결제
     */
    @Transactional
    public Payment processPayment(Long reservationId, PaymentType paymentType) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

        String transactionId = UUID.randomUUID().toString();

        Payment payment = Payment.createPayment(
                reservation, paymentType, reservation.getTotalAmount(), transactionId);
        paymentRepository.save(payment);

        // 외부요청
        PaymentRequest paymentRequest = PaymentRequest.from(payment);

        try {
            PaymentResponse response = paymentProcessor.execute(paymentType, paymentRequest);
            payment.complete(response);

        } catch (Exception e) {
            payment.fail(e.getMessage());
            throw new IllegalArgumentException("결제 중 오류 발생");
        }
        return payment;
    }

    @Transactional
    public void handleVirtualAccountWebhook(String transactionId, String statusString) {
        log.info("[Webhook] 가상계좌 입금 확인 시작 - transactionId: {}", transactionId);

        // 거래 ID로 결제 찾기
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> {
                    log.error("결제 정보 없음: {}", transactionId);
                    return new IllegalArgumentException("해당 거래 ID의 결제를 찾을 수 없습니다: " + transactionId);
                });

        log.info("찾은 결제 정보 - ID: {}, 현재상태: {}, 금액: {}",
                payment.getId(), payment.getStatus(), payment.getAmount());

        // 현재 상태 확인 (중복 처리 방지)
        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            log.warn("이미 완료된 결제입니다. 중복 웹훅 무시: {}", transactionId);
            return;
        }

        if (payment.getStatus() == PaymentStatus.FAILED) {
            log.warn("실패한 결제입니다. 웹훅 무시: {}", transactionId);
            return;
        }

        // 상태 변환
        PaymentStatus newStatus;
        try {
            newStatus = PaymentStatus.valueOf(statusString);
        } catch (IllegalArgumentException e) {
            log.error("잘못된 상태값: {}", statusString);
            throw new IllegalArgumentException("유효하지 않은 상태값입니다: " + statusString);
        }

        // 상태 업데이트(dirt checking)
        payment.updateStatusFromWebhook(newStatus);

        log.info("[Webhook] 결제 상태 업데이트 완료 - {} → {}",
                PaymentStatus.PENDING, newStatus);

        // 후속 처리
        if (newStatus == PaymentStatus.COMPLETED) {
            handlePaymentCompleted(payment);
        }
    }

    /**
     * 결제 완료 후 후속 처리
     */
    private void handlePaymentCompleted(Payment payment) {
        log.info("결제 완료 후속 처리 시작 - paymentId: {}", payment.getId());

        // TODO: 예약 확정 처리
        // reservationService.confirmReservation(payment.getReservationId());

        // TODO: 알림 발송
        // notificationService.sendPaymentCompletedNotification(payment);

        log.info("결제 완료 후속 처리 완료");
    }
}
