package com.example.meetupflow.common.exception;

public class MeetingRoomNotFoundException extends BusinessException {
    public MeetingRoomNotFoundException(Long meetingRoomId) {
        super(ErrorCode.MEETING_ROOM_NOT_FOUND,
                String.format("회의실을 찾을 수 없습니다: meetingRoomId=%d", meetingRoomId)
        );
    }

}
