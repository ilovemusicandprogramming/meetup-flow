package com.example.meetupflow.controller;

import com.example.meetupflow.common.ApiResponse;
import com.example.meetupflow.common.Result;
import com.example.meetupflow.common.exception.MeetingRoomNotFoundException;
import com.example.meetupflow.common.exception.reservation.ReservationNotFoundException;
import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.meetingRoom.UpdateMeetingRoomResponse;
import com.example.meetupflow.dto.meetingRoom.*;
import com.example.meetupflow.dto.user.UpdateUserResponse;
import com.example.meetupflow.service.MeetingRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meetingRooms")
@RequiredArgsConstructor
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MeetingRoomListResponse>>> list (){
        return ResponseEntity.ok(ApiResponse.success(meetingRoomService.findMeetingRooms(), "회의실 목록 조회 성공"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MeetingRoomResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(meetingRoomService.findMeetingRoom(id), "회의실 상세 조회 성공"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateMeetingRoomResponse>> create(@RequestBody @Valid CreateMeetingRoomRequest request) {
        return ResponseEntity.ok(ApiResponse.success(meetingRoomService.createMeetingRoom(request.name(), request.capacity(), request.hourlyRate()), "회의실 생성 성공"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateMeetingRoomResponse>> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateMeetingRoomRequest request){
        return ResponseEntity.ok(ApiResponse.success(meetingRoomService.updateMeetingRoom(id, request.name(), request.capacity(), request.hourlyRate()), "회의실 정보 수정 성공"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        meetingRoomService.deleteMeetingRoom(id);
        return ResponseEntity.noContent().build();
    }
}
