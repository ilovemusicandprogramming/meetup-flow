package com.example.meetupflow.common;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.domain.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct // 👈 의존관계 주입이 완료된 후 실행됨
    public void init() {
        initService.dbInit1(); // 사용자, 회의실 등 기초 데이터
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            User user1 = User.createUser("차윤호", "aaa@mail.com", new Address("서울", "마포구", "14545"));
//            User user2 = User.createUser("홍길동", "bbb@mail.com", new Address("강원", "삼척시", "78978"));
            em.persist(user1);
//            em.persist(user2);

            MeetingRoom room = MeetingRoom.createMeetingRoom("대회의실 A", 10, 50000);
            em.persist(room);

            LocalDateTime start = LocalDateTime.of(2026, 1, 20, 14, 0); // 2026년 1월 20일 14시 0분
            LocalDateTime end = LocalDateTime.of(2026, 1, 20, 16, 0);   // 2026년 1월 20일 16시 0분
            Reservation res = Reservation.createReservation(room, user1, start, end);
            em.persist(res);
        }
    }
}
