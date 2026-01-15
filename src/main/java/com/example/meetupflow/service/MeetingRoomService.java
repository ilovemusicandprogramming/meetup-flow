package com.example.meetupflow.service;

import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.repository.MeetingRoomRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * 미팅룸목록조회
     */
    public List<MeetingRoom> findMeetingRoom() {
        return meetingRoomRepository.findAll();
    }

    /**
     * 미팅룸생성
     */
    public Long createMeetingRoom(String name, int capacity, int hourlyRate) {
        MeetingRoom meetingRoom = MeetingRoom.createMeetingRoom(name, capacity, hourlyRate);
        meetingRoomRepository.save(meetingRoom);

        return meetingRoom.getId();
    }
}
