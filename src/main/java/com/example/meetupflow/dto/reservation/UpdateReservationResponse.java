package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateReservationResponse {
    private Long meetingRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public UpdateReservationResponse(Reservation reservation) {
        meetingRoomId = reservation.getMeetingRoom().getId();
        startTime = reservation.getStartTime();
        endTime = reservation.getEndTime();
    }
}
