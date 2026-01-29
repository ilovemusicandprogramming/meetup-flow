package com.example.meetupflow.controller;

import com.example.meetupflow.common.ApiResponse;
import com.example.meetupflow.dto.reservation.*;
import com.example.meetupflow.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservationListResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.success(reservationService.findReservations(), "예약 목록 조회 성공"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservationsResponse>> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(reservationService.findReservation(id), "예약 상세 조회 성공"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateReservationResponse>> create(@RequestBody @Valid CreateReservationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(reservationService.createReservation(
                        request.meetingRoomId(), request.startTime(), request.endTime(), request.userId()),"예약 생성 완료"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateReservationResponse>> update(
            @PathVariable("id") Long id, @RequestBody @Valid UpdateReservationRequest request) {

        return ResponseEntity.ok(ApiResponse.success(reservationService.updateReservation(id, request.meetingRoomId(), request.startTime(), request.endTime()), "예약 수정 완료"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
