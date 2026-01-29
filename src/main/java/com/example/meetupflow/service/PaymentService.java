package com.example.meetupflow.service;

import com.example.meetupflow.common.exception.payment.DuplicatePaymentException;
import com.example.meetupflow.common.exception.payment.PaymentNotFoundException;
import com.example.meetupflow.common.exception.payment.PaymentProcessingException;
import com.example.meetupflow.common.exception.reservation.ReservationNotFoundException;
import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.PaymentStatus;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.CreatePaymentResponse;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.repository.PaymentRepository;
import com.example.meetupflow.repository.ReservationRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PaymentResponse findPayment(Long paymentId) {
        return PaymentResponse.from(paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId)));
    }

    /**
     * 결제
     */
    @Transactional
    public CreatePaymentResponse processPayment(Long reservationId, PaymentType paymentType) {
        Payment payment = createPendingPayment(reservationId, paymentType);

        // 외부요청
        PaymentRequest request = PaymentRequest.from(payment);
        PaymentResponse response = executeExternalPayment(paymentType, request, payment);

        return CreatePaymentResponse.from(updatePaymentResult(payment.getId(), response));
    }

    @Transactional
    protected Payment createPendingPayment(Long reservationId, PaymentType paymentType) {
        if (paymentRepository.existsByReservationId(reservationId)) {
            throw new DuplicatePaymentException(reservationId);
        }

        Reservation reservation = getReservation(reservationId);

        Payment payment = Payment.createPayment(
                reservation, paymentType, reservation.getTotalAmount());

        // unique check -> db 저장
        try {
            paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            log.error("중복 결제 시도 (DB 제약): reservationId={}", reservationId);
            throw new DuplicatePaymentException(reservationId);
        }
        return payment;
    }

    protected PaymentResponse executeExternalPayment(
            PaymentType paymentType, PaymentRequest request, Payment payment) {
        try {
            return paymentProcessor.execute(paymentType, request);
        } catch (PaymentProcessingException e) {
            // PG사 명시적 오류
            log.error("PG사 처리 실패: {}", e.getMessage());
            payment.fail(e.getMessage());
            throw e;

        } catch (Exception e) {
            // 예상치 못한 오류 (네트워크 등)
            log.error("결제 처리 중 시스템 오류", e);
            payment.fail("시스템 오류");
            throw new PaymentProcessingException("결제 처리 실패", e);
        }
    }

    private Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    @Transactional
    protected Payment updatePaymentResult(Long paymentId, PaymentResponse response) {
        Payment payment = getPayment(paymentId);
        payment.complete(response);
        return payment;
    }

    /**
     * 가상계좌 입금시 웹훅
     */
    @Transactional
    public void handleVirtualAccountWebhook(String transactionId, String statusString) {
        log.info("[Webhook] 가상계좌 입금 확인 시작 - transactionId: {}", transactionId);

        try {

            // 거래 ID로 결제 찾기
            Payment payment = findPaymentByTransactionId(transactionId);

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

            // 상태 업데이트(dirty checking)
            payment.updateStatusFromWebhook(newStatus);

            log.info("[Webhook] 결제 상태 업데이트 완료 - {} → {}",
                    PaymentStatus.PENDING, newStatus);

            // 후속 처리
            if (newStatus == PaymentStatus.COMPLETED) {
                handlePaymentCompleted(payment);
            }
        } catch (OptimisticLockException e) {
            log.warn("동시 웹훅 처리 감지, 무시: transactionId={}", transactionId);
        }
    }

    private Payment findPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> {
                    log.error("결제 정보 없음: {}", transactionId);
                    return new PaymentNotFoundException(transactionId);
                });
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

    //===== 기타 메서드=====/
    private Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }
}
