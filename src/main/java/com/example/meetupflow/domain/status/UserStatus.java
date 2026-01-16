package com.example.meetupflow.domain.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    NORMAL("정상"),
    WITHDRAWAL("탈퇴"),
    DORMANT("휴면");

    // 상태에 대한 한글 설명을 저장하기 위한 필드
    private final String description;
}
