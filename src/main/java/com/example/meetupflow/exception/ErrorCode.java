package com.example.meetupflow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Reservation
    RESERVATION_NOT_FOUND(404, "RES001", "존재하지 않는 예약입니다."),
    NOT_MODIFIABLE_STATUS(400, "RES002", "수정할 수 없는 예약 상태입니다."),
    INVALID_TIME_SLOT(400, "RES003", "잘못된 예약 시간입니다."),
    TIME_ALREADY_OCCUPIED(409, "RES004", "이미 해당 시간에 예약이 존재합니다.");

    private final int status;
    private final String code;
    private final String message;

}
