package com.example.meetupflow.domain;

import com.example.meetupflow.common.BaseEntity;
import com.example.meetupflow.domain.status.ReservationStatus;
import com.example.meetupflow.common.exception.reservation.ReservationNotModifiableException;
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
        reservation.status = ReservationStatus.PENDING;

        return reservation;
    }

    public void updateReservation(MeetingRoom meetingRoom, LocalDateTime startTime, LocalDateTime endTime) {
        if(this.status == ReservationStatus.CANCELLED) {
            throw new ReservationNotModifiableException();
        }

        this.meetingRoom = meetingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hourlyRateSnapshot = meetingRoom.getHourlyRate();
        this.totalAmount = calculateTotalAmount(this.hourlyRateSnapshot, startTime, endTime);
    }

    public void changeStatusToCancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public void confirm() {
        if (this.status == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("이미 취소된 예약은 확정할 수 없습니다.");
        }

        if (this.status == ReservationStatus.CONFIRMED) {
            return;
        }

        this.status = ReservationStatus.CONFIRMED;
    }

    private static double calculateTotalAmount(int hourlyRate, LocalDateTime startTime, LocalDateTime endTime) {
        long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
        double hours = minutes / 60.0;
        return hours * hourlyRate;
    }


}
