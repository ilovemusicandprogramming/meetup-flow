package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.dto.meetingRoom.CreateMeetingRoomRequest;
import com.example.meetupflow.dto.meetingRoom.CreateMeetingRoomResponse;
import com.example.meetupflow.dto.meetingRoom.MeetingRoomListResponse;
import com.example.meetupflow.service.MeetingRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @GetMapping("/meetingRooms")
    public Result list (){
        List<MeetingRoom> findMeetingRoom = meetingRoomService.findMeetingRoom();
        List<MeetingRoomListResponse> collect = findMeetingRoom.stream()
                .map(MeetingRoomListResponse::new)
                .toList();
        return new Result(collect.size(), collect);
    }

    @PostMapping("/meetingRooms")
    public CreateMeetingRoomResponse create(@RequestBody @Valid CreateMeetingRoomRequest request) {
        Long id = meetingRoomService.createMeetingRoom(request.getName(), request.getCapacity(), request.getHourlyRate());
        return new CreateMeetingRoomResponse(id);
    }
}
