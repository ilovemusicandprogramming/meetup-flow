package com.example.meetupflow.service;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.repository.MeetingRoomRepository;
import com.example.meetupflow.repository.ReservationRepository;
import com.example.meetupflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * 예약목록조회
     */
    public List<Reservation> findReservations() {
        return reservationRepository.findAll();
    }

    /**
     * 예약생성
     */
    public Long createReservation(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long userId) {
        User user = userRepository.findById(userId).get();
        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId).get();

        Reservation reservation = Reservation.createReservation(meetingRoom, user, startTime, endTime);

        reservationRepository.save(reservation);
        return reservation.getId();
    }
}
