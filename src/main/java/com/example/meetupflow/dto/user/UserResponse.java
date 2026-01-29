package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.reservation.ReservationDto;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        Address address,
        List<ReservationDto> reservations
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getReservations().stream()
                        .map(ReservationDto::from)
                        .toList()
        );
    }
}