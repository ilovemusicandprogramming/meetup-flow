package com.example.meetupflow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateReservationResponse {
    private Long id;

    public CreateReservationResponse(Long id) {
        this.id = id;
    }
}
