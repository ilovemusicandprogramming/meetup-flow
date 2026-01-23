package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.domain.status.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
public class UpdateReservationResponse {
    private Long meetingRoomId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;

    public UpdateReservationResponse(Reservation reservation) {
        meetingRoomId = reservation.getMeetingRoom().getId();
        userName = reservation.getUser().getName();
        startTime = reservation.getStartTime();
        endTime = reservation.getEndTime();
        status = reservation.getStatus();
    }
}
