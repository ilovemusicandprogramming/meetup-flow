package com.example.meetupflow.dto.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateReservationRequest {

    private Long meetingRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long userId;


}
