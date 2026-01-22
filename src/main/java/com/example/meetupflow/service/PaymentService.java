package com.example.meetupflow.service;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.PaymentType;
import com.example.meetupflow.dto.payment.PaymentRequest;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.repository.PaymentRepository;
import com.example.meetupflow.repository.ReservationRepository;
import com.querydsl.core.dml.DMLClause;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentProcessor paymentProcessor;

    /**
     * 결제단건조회
     */
    @Transactional(readOnly = true)
    public Payment findOne(Long id) {
        return paymentRepository.findById(id).get();
    }

    /**
     * 결제
     */
    @Transactional
    public Long processPayment(Long reservationId, PaymentType paymentType) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
        String transactionId = UUID.randomUUID().toString();

        Payment payment = Payment.createPayment(reservation, paymentType, reservation.getTotalAmount(), transactionId);
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
        return payment.getId();
    }
}
