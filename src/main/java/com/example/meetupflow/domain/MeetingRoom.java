package com.example.meetupflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MeetingRoom {

    @Id
    @GeneratedValue
    @Column(name = "meetingRoom_id")
    private Long Id;

    private String name;

    private int capacity;

    private double hourlyRate;
}
