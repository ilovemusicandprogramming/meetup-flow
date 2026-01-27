package com.example.meetupflow.controller;

import com.example.meetupflow.common.ApiResponse;
import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.dto.payment.CreatePaymentRequest;
import com.example.meetupflow.dto.payment.CreatePaymentResponse;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentId}/payments")
    public ResponseEntity<ApiResponse<PaymentResponse>> get(@PathVariable("paymentId") Long paymentId) {
        return ResponseEntity.ok(ApiResponse.success(PaymentResponse.from(paymentService.findOne(paymentId)), "결제 상세 조회 성공"));
    }

    @PostMapping("/{reservationId}/payments")
    public ResponseEntity<ApiResponse<CreatePaymentResponse>> create(@PathVariable("reservationId") Long reservationId, @RequestBody @Valid CreatePaymentRequest request) {
        Payment payment = paymentService.processPayment(reservationId, request.getPaymentType());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreatePaymentResponse.from(payment), "결제 완료"));
    }
}
