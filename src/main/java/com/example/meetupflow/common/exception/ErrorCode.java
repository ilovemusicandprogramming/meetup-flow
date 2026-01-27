package com.example.meetupflow.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Payment 관련
    PAYMENT_NOT_FOUND("P001", "결제 정보를 찾을 수 없습니다"),
    DUPLICATE_PAYMENT("P002", "이미 결제가 진행 중입니다"),
    INVALID_PAYMENT_STATUS("P003", "유효하지 않은 결제 상태입니다"),
    PAYMENT_PROCESSING_FAILED("P004", "결제 처리 중 오류가 발생했습니다"),
    PAYMENT_ALREADY_COMPLETED("P005", "이미 완료된 결제입니다"),
    PAYMENT_TIMEOUT("P006", "결제 처리 시간이 초과되었습니다"),
    NOT_MODIFIABLE_STATUS("P007", "결제 상태를 변경할 수 없습니다."),

    // Reservation 관련
    RESERVATION_NOT_FOUND("R001", "예약 정보를 찾을 수 없습니다"),
    RESERVATION_ALREADY_EXISTS("R002", "이미 예약이 존재합니다"),
    RESERVATION_TIME_CONFLICT("R003", "해당 시간에 이미 예약이 있습니다"),

    // MeetingRoom 관련
    MEETING_ROOM_NOT_FOUND("M001", "회의실을 찾을 수 없습니다"),
    MEETING_ROOM_NOT_AVAILABLE("M002", "사용할 수 없는 회의실입니다"),

    // Common 관련
    INVALID_INPUT_VALUE("C001", "잘못된 입력값입니다"),
    INTERNAL_SERVER_ERROR("C002", "서버 내부 오류가 발생했습니다"),
    UNAUTHORIZED("C003", "인증이 필요합니다"),
    FORBIDDEN("C004", "접근 권한이 없습니다"),
    OPTIMISTIC_LOCK_CONFLICT("C005", "다른 요청이 처리 중입니다. 잠시 후 다시 시도해주세요");

    private final String code;
    private final String message;

}
