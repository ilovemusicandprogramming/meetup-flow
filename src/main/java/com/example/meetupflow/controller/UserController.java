package com.example.meetupflow.controller;

import com.example.meetupflow.common.ApiResponse;
import com.example.meetupflow.common.Result;
import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.user.*;
import com.example.meetupflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserListResponse>>> list(){
        return ResponseEntity.ok(ApiResponse.success(userService.findUsers(), "회원 목록 조회 성공"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> get(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(userService.findUser(id), "회원 상세 조회 성공"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateUserResponse>> create(@RequestBody @Valid CreateUserRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(userService.join(request.name(), request.email(), request.address()),"회원 가입 완료"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateUserResponse>> update(
            @PathVariable("id") Long id, @RequestBody @Valid UpdateUserRequest request){
        Address newAddress = new Address(
                request.address().getCity(),
                request.address().getStreet(),
                request.address().getZipcode()
        );

        return ResponseEntity.ok(ApiResponse.success(userService.updateUser(id, request.email(), newAddress), "회원정보 수정 완료"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdraw(@PathVariable Long id) {
        userService.withdrawUser(id);
        return ResponseEntity.noContent().build();
    }
}
