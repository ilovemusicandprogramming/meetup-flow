package com.example.meetupflow.repository.impl;

import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.status.ReservationStatus;
import com.example.meetupflow.repository.ReservationRepositoryCustom;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.meetupflow.domain.QReservation.reservation;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> findOverlapping(Long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime, Long currentId) {
        return queryFactory
                .selectFrom(reservation)
                .where(
                        reservation.meetingRoom.id.eq(meetingRoomId),
                        reservation.startTime.lt(endTime),  // 기존 예약 시작 < 나의 종료 시간
                        reservation.endTime.gt(startTime),   // 기존 예약 종료 > 나의 시작 시간
                        reservation.status.ne(ReservationStatus.CANCELLED), // 취소된 예약은 제외
                        currentIdNotEq(currentId)           // 수정 시 본인 제외 로직
                )
                .fetch();
    }

    private BooleanExpression currentIdNotEq(Long currentId) {
        // currentId 가 null 이면 무시, 값있으면 해당 id 제외
        return currentId != null ? reservation.id.ne(currentId) :null;
    }
}
