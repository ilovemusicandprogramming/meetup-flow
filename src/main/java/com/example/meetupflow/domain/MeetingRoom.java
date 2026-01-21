package com.example.meetupflow.domain;

import com.example.meetupflow.common.BaseEntity;
import com.example.meetupflow.domain.status.MeetingRoomStatus;
import com.example.meetupflow.domain.status.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MeetingRoom extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "meeting_room_id")
    private Long id;
    private String name;
    private int capacity;
    private int hourlyRate;
    @Enumerated(EnumType.STRING)
    private MeetingRoomStatus status;
    @OneToMany(mappedBy = "meetingRoom")
    private List<Reservation> reservations = new ArrayList<>();

    public static MeetingRoom createMeetingRoom(String name, int capacity, int hourlyRate) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("미팅룸 이름은 필수입니다.");
        }

        if(capacity <= 0 ){
            throw new IllegalArgumentException("수용 인원은 1명 이상이어야 합니다.");
        }

        if(hourlyRate < 0 ){
            throw new IllegalArgumentException("시간당 요금은 0원 이상이어야 합니다.");
        }

        MeetingRoom meetingRoom = new MeetingRoom();

        meetingRoom.name = name;
        meetingRoom.capacity = capacity;
        meetingRoom.hourlyRate = hourlyRate;
        meetingRoom.status = MeetingRoomStatus.AVAILABLE;

        return meetingRoom;
    }

    public void updateProfile(String name, int capacity, int hourlyRate) {
        if(!this.reservations.isEmpty()){
            throw new IllegalStateException("이미 예약된 건이 있는 회의실은 정보를 수정할 수 없습니다.");
        }

        this.name = name;
        this.capacity = capacity;
        this.hourlyRate = hourlyRate;
    }

    public void changeStatusToDeleted() {
        this.status = MeetingRoomStatus.DELETED;
    }
}
