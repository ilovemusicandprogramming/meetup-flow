package com.example.meetupflow.dto.reservation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateReservationRequest {

    private Long meetingRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long userId;


}
