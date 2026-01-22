package com.example.meetupflow.dto.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePaymentResponse {
    private Long id;

    public CreatePaymentResponse(Long id) {
        this.id = id;
    }
}
