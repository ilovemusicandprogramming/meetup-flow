package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.meetingRoom.UpdateMeetingRoomResponse;
import com.example.meetupflow.dto.meetingRoom.*;
import com.example.meetupflow.dto.user.UpdateUserResponse;
import com.example.meetupflow.service.MeetingRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/meetingRooms/{id}")
    public MeetingRoomResponse get(@PathVariable Long id) {
        MeetingRoom findMeetingRoom = meetingRoomService.findOne(id);
        return new MeetingRoomResponse(findMeetingRoom);
    }

    @PostMapping("/meetingRooms")
    public CreateMeetingRoomResponse create(@RequestBody @Valid CreateMeetingRoomRequest request) {
        Long id = meetingRoomService.createMeetingRoom(request.getName(), request.getCapacity(), request.getHourlyRate());
        return new CreateMeetingRoomResponse(id);
    }

    @PatchMapping("/meetingRooms/{id}")
    public UpdateMeetingRoomResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateMeetingRoomRequest request){
        meetingRoomService.updateMeetingRoom(id, request.getName(), request.getCapacity(), request.getHourlyRate());
        MeetingRoom updateMeetingRoom = meetingRoomService.findOne(id);
        return new UpdateMeetingRoomResponse(updateMeetingRoom);
    }
}
