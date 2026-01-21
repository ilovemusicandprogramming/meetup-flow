package com.example.meetupflow.repository;

import com.example.meetupflow.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom {
}
