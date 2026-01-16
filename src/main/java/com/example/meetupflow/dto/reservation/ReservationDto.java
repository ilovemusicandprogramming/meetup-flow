package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reservationAt;
    private ReservationStatus status;

    public ReservationDto(Reservation reservation) {
        id = reservation.getId();
        startTime = reservation.getStartTime();
        endTime = reservation.getEndTime();
        reservationAt = reservation.getReservationAt();
        status = reservation.getStatus();
    }
}
