package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.user.CreateUserRequest;
import com.example.meetupflow.dto.user.CreateUserResponse;
import com.example.meetupflow.dto.user.UserListResponse;
import com.example.meetupflow.dto.user.UserResponse;
import com.example.meetupflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public Result list(){
        List<User> findUsers = userService.findUsers();
        List<UserListResponse> collect = findUsers.stream()
                .map(u -> new UserListResponse(u.getName(), u.getEmail(), u.getAddress()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @GetMapping("/users/{id}")
    public UserResponse get(@PathVariable Long id){
        User findUser = userService.findOne(id);
//        return new UserResponse(findUser.getName(), findUser.getEmail(), findUser.getAddress(), findUser.getReservations());
        return new UserResponse(findUser);
    }

    @PostMapping("/users")
    public CreateUserResponse create(@RequestBody @Valid CreateUserRequest request) {
        Long id = userService.join(request.getName(), request.getEmail(), request.getAddress());
        return new CreateUserResponse(id);
    }
}
