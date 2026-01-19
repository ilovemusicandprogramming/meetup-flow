package com.example.meetupflow.domain.status;

public enum MeetingRoomStatus {
    AVAILABLE,    // 사용 가능 (정상 운영 중)
    UNAVAILABLE,  // 일시 중단 (수리 중, 내부 사정으로 일시 폐쇄)
    DELETED       // 삭제됨 (논리 삭제 상태)
}
