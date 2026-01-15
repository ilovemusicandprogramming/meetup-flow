package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.dto.reservation.CreateReservationRequest;
import com.example.meetupflow.dto.reservation.CreateReservationResponse;
import com.example.meetupflow.dto.reservation.ReservationListResponse;
import com.example.meetupflow.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public Result list (){
        List<Reservation> findReservations = reservationService.findReservations();
        List<ReservationListResponse> collect = findReservations.stream()
                .map(ReservationListResponse::new)
                .toList();
        return new Result(collect.size(), collect);
    }

    @PostMapping("/reservations")
    public CreateReservationResponse create(@RequestBody @Valid CreateReservationRequest request){
        Long id = reservationService.createReservation(
                request.getMeetingRoomId(), request.getStartTime(), request.getEndTime(), request.getUserId());

        return new CreateReservationResponse(id);
    }
}
