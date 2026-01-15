package com.example.meetupflow.dto;

import com.example.meetupflow.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
public class ReservationListResponse {

    private Long reservationId;
    private Long meetingRoomId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reservationAt;
    private double totalAmount;
    private ReservationStatus status;

    public ReservationListResponse(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.meetingRoomId = reservation.getMeetingRoom().getId();
        this.userName = reservation.getUser().getName();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.reservationAt = reservation.getReservationAt();
        this.totalAmount = reservation.getTotalAmount();
        this.status = reservation.getStatus();
    }
}
