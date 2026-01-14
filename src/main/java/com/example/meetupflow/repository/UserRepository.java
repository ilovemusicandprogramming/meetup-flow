package com.example.meetupflow.repository;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
