package com.example.meetupflow.dto.meetingRoom;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.dto.reservation.ReservationDto;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MeetingRoomResponse {
    private Long id;
    private String name;
    private int capacity;
    private int hourlyRate;
    private List<ReservationDto> reservations;

    public MeetingRoomResponse(MeetingRoom meetingRoom) {
        id = meetingRoom.getId();
        name = meetingRoom.getName();
        capacity = meetingRoom.getCapacity();
        hourlyRate = meetingRoom.getHourlyRate();
        reservations = meetingRoom.getReservations().stream()
                .map(reservation -> new ReservationDto(reservation))
                .collect(Collectors.toList());
    }
}
