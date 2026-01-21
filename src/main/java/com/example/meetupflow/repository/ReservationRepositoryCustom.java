package com.example.meetupflow.repository;

import com.example.meetupflow.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> findOverlapping(
            Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long currentId);
}
