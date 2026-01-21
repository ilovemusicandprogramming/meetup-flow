package com.example.meetupflow.repository;

import com.example.meetupflow.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {

    @Query("select r from Reservation r join fetch r.user join fetch r.meetingRoom")
    List<Reservation> findAllWithUserAndRoom();
}
