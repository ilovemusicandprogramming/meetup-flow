package com.example.meetupflow.dto.user;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.reservation.ReservationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private Address address;
    private List<ReservationDto> reservations;

    public UserResponse(User user) {
        name = user.getName();
        email = user.getEmail();
        address = user.getAddress();
        reservations = user.getReservations().stream()
                .map(reservation -> new ReservationDto(reservation))
                .collect(Collectors.toList());
    }
}
