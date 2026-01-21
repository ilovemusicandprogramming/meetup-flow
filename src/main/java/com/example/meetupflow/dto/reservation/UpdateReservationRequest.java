package com.example.meetupflow.dto.reservation;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.domain.status.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
public class UpdateReservationRequest {
    private Long meetingRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
