package com.example.meetupflow.controller;

import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.user.*;
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
        return new UserResponse(findUser);
    }

    @PostMapping("/users")
    public CreateUserResponse create(@RequestBody @Valid CreateUserRequest request) {
        Long id = userService.join(request.getName(), request.getEmail(), request.getAddress());
        return new CreateUserResponse(id);
    }

    @PatchMapping("/users/{id}")
    public UpdateUserResponse update(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest request){
        Address newAddress = new Address(
                request.getAddress().getCity(),
                request.getAddress().getStreet(),
                request.getAddress().getZipcode()
        );

        userService.updateUser(id, request.getEmail(), newAddress);
        User updateUser = userService.findOne(id);
        return new UpdateUserResponse(updateUser);
    }
}
