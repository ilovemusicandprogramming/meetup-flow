package com.example.meetupflow.service;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.domain.status.ReservationStatus;
import com.example.meetupflow.repository.MeetingRoomRepository;
import com.example.meetupflow.repository.ReservationRepository;
import com.example.meetupflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * 예약목록조회
     */
    @Transactional(readOnly = true)
    public List<Reservation> findReservations() {
//        return reservationRepository.findAll();
        return reservationRepository.findAllWithUserAndRoom();
    }

    /**
     * 예약단건조회
     */
    @Transactional(readOnly = true)
    public Reservation findOne(Long id) {
        return reservationRepository.findById(id).get();
    }

    /**
     * 예약생성
     */
    @Transactional
    public Long createReservation(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회의실입니다."));

        // validation
        validateReservationTime(startTime, endTime);
        checkTimeAvailability(meetingRoom.getId(), startTime, endTime, null);

        Reservation reservation = Reservation.createReservation(meetingRoom, user, startTime, endTime);

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    /**
     * 예약수정
     */
    @Transactional
    public void updateReservation(Long reservationId, Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

        if (reservation.getStatus() == ReservationStatus.PAID) {
            throw new IllegalStateException("결제가 완료된 예약은 수정할 수 없습니다.");
        }

        MeetingRoom meetingRoom = meetingRoomRepository.findById(meetingRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회의실입니다."));

        // validation
        validateReservationTime(startTime, endTime);
        checkTimeAvailability(meetingRoom.getId(), startTime, endTime, reservationId);

        reservation.updateReservation(meetingRoom, startTime, endTime);
    }


    /**
     * 예약취소
     */
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();

        LocalDateTime now = LocalDateTime.now();

        if (reservation.getStartTime().minusDays(1).isBefore(now)) {
            throw new IllegalStateException("예약 취소는 시작 시간 24시간 전까지만 가능합니다.");
        }

        reservation.changeStatusToCancel();
    }
    
    private void validateReservationTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new IllegalArgumentException("종료 시간은 시작 시간보다 이후여야 합니다.");
        }

        if (isNOtMultipleOf30Minutes(startTime) || isNOtMultipleOf30Minutes(endTime)) {
            throw new IllegalArgumentException("예약은 30분 단위로만 가능합니다. (예: 14:00, 14:30)");
        }

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("과거 시간으로 예약할 수 없습니다.");
        }
    }

    private boolean isNOtMultipleOf30Minutes(LocalDateTime time) {
        int minute = time.getMinute();
        return minute != 0 && minute != 30;
    }

    private void checkTimeAvailability(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long currentReservationId) {
        List<Reservation> overlapping = reservationRepository.findOverlapping(meetingRoomId, startTime, endTime, currentReservationId);

        if(!overlapping.isEmpty()){
            throw new IllegalArgumentException("해당 시간대에 이미 예약이 존재합니다.");
        }
    }
}
