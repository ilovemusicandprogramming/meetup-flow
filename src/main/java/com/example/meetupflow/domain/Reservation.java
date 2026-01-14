package com.example.meetupflow.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime reservationAt;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
