package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.Reservation;
import com.example.meetupflow.dto.reservation.*;
import com.example.meetupflow.dto.user.UpdateUserRequest;
import com.example.meetupflow.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public Result list() {
        List<Reservation> findReservations = reservationService.findReservations();
        List<ReservationListResponse> collect = findReservations.stream()
                .map(ReservationListResponse::new)
                .toList();
        return new Result(collect.size(), collect);
    }

    @GetMapping("/reservations/{id}")
    public ReservationsResponse get(@PathVariable("id") Long id) {
        Reservation findReservation = reservationService.findOne(id);
        return new ReservationsResponse(findReservation);
    }

    @PostMapping("/reservations")
    public CreateReservationResponse create(@RequestBody @Valid CreateReservationRequest request) {
        Long id = reservationService.createReservation(
                request.getMeetingRoomId(), request.getStartTime(), request.getEndTime(), request.getUserId());

        return new CreateReservationResponse(id);
    }

    @PatchMapping("/reservations/{id}")
    public UpdateReservationResponse update(
            @PathVariable("id") Long id, @RequestBody @Valid UpdateReservationRequest request) {
        reservationService.updateReservation(id, request.getMeetingRoomId(), request.getStartTime(), request.getEndTime());

        Reservation updateReservation = reservationService.findOne(id);
        return new UpdateReservationResponse(updateReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
