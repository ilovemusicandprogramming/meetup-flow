package com.example.meetupflow.controller;

import com.example.meetupflow.domain.Payment;
import com.example.meetupflow.dto.payment.CreatePaymentRequest;
import com.example.meetupflow.dto.payment.CreatePaymentResponse;
import com.example.meetupflow.dto.payment.PaymentResponse;
import com.example.meetupflow.dto.reservation.CreateReservationRequest;
import com.example.meetupflow.dto.reservation.CreateReservationResponse;
import com.example.meetupflow.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/reservations/{paymentId}/payments")
    public PaymentResponse get(@PathVariable("paymentId") Long paymentId) {
        Payment findPayment = paymentService.findOne(paymentId);
        return new PaymentResponse(findPayment);
    }

    @PostMapping("/reservations/{reservationId}/payments")
    public CreatePaymentResponse create(@PathVariable("reservationId") Long reservationId, @RequestBody @Valid CreatePaymentRequest request) {
        Long id = paymentService.processPayment(reservationId, request.getPaymentType());
        return new CreatePaymentResponse(id);
    }
}
