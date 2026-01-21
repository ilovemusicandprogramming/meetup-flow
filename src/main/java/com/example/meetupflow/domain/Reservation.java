package com.example.meetupflow.domain;

import com.example.meetupflow.common.BaseEntity;
import com.example.meetupflow.domain.status.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter
public class Reservation extends BaseEntity {

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
    private int hourlyRateSnapshot;
    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public static Reservation createReservation(MeetingRoom meetingRoom, User user, LocalDateTime startTime, LocalDateTime endTime) {
        Reservation reservation = new Reservation();

        reservation.meetingRoom = meetingRoom;
        reservation.user = user;
        reservation.startTime = startTime;
        reservation.endTime = endTime;
        reservation.reservationAt = LocalDateTime.now();
        reservation.hourlyRateSnapshot = meetingRoom.getHourlyRate();
        reservation.totalAmount = calculateTotalAmount(reservation.hourlyRateSnapshot, startTime, endTime);
        reservation.status = ReservationStatus.PENDING; // 예약 대기 상태 등

        return reservation;
    }

    private static double calculateTotalAmount(int hourlyRate, LocalDateTime startTime, LocalDateTime endTime) {
        long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
        long hours = (minutes + 59) / 60; // 올림
        return hours * hourlyRate;
    }
}
