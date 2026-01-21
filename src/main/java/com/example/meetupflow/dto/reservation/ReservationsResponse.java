package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.domain.status.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationsResponse {
    private Long id;
    private String meetingRoomName;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reservationAt;
    private double totalAmount;
    private ReservationStatus status;

    public ReservationsResponse(Reservation reservation) {
        id = reservation.getId();
        meetingRoomName = reservation.getMeetingRoom().getName();
        userName = reservation.getUser().getName();
        startTime = reservation.getStartTime();
        endTime = reservation.getEndTime();
        reservationAt = reservation.getReservationAt();
        totalAmount = reservation.getTotalAmount();
        status = reservation.getStatus();
    }
}
