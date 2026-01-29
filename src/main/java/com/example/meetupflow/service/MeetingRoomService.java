package com.example.meetupflow.service;

import com.example.meetupflow.common.exception.MeetingRoomNotFoundException;
import com.example.meetupflow.domain.MeetingRoom;
import com.example.meetupflow.dto.meetingRoom.CreateMeetingRoomResponse;
import com.example.meetupflow.dto.meetingRoom.MeetingRoomListResponse;
import com.example.meetupflow.dto.meetingRoom.MeetingRoomResponse;
import com.example.meetupflow.dto.meetingRoom.UpdateMeetingRoomResponse;
import com.example.meetupflow.repository.MeetingRoomRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * 미팅룸목록조회
     */
    @Transactional(readOnly = true)
    public List<MeetingRoomListResponse> findMeetingRooms() {
        List<MeetingRoom> meetingRooms = meetingRoomRepository.findAll();
        return meetingRooms.stream()
                .map(MeetingRoomListResponse::new)
                .toList();
    }

    /**
     * 미팅룸생성
     */
    @Transactional
    public CreateMeetingRoomResponse createMeetingRoom(String name, int capacity, int hourlyRate) {
        MeetingRoom meetingRoom = MeetingRoom.createMeetingRoom(name, capacity, hourlyRate);
        meetingRoomRepository.save(meetingRoom);

        return CreateMeetingRoomResponse.from(meetingRoom);
    }

    /**
     * 미팅룸단건조회
     */
    @Transactional(readOnly = true)
    public MeetingRoomResponse findMeetingRoom(Long id) {
        MeetingRoom meetingRoom = getMeetingRoom(id);
        return MeetingRoomResponse.from(meetingRoom);
    }

    /**
     * 미팅룸정보수정
     */
    @Transactional
    public UpdateMeetingRoomResponse updateMeetingRoom(Long id, String name, int capacity, int hourlyRate) {
        MeetingRoom meetingRoom = getMeetingRoom(id);
        meetingRoom.updateProfile(name, capacity, hourlyRate);
        return UpdateMeetingRoomResponse.from(meetingRoom);
    }

    /**
     * 미팅룸삭제
     */
    @Transactional
    public void deleteMeetingRoom(Long id) {
        MeetingRoom meetingRoom = getMeetingRoom(id);
        meetingRoom.changeStatusToDeleted();
    }

    //==== 기타메서드 =====
    private MeetingRoom getMeetingRoom(Long id) {
        return meetingRoomRepository.findById(id)
                .orElseThrow(() -> new MeetingRoomNotFoundException(id));
    }
}
