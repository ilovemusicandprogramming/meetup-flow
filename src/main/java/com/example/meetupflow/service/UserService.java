package com.example.meetupflow.service;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.user.*;
import com.example.meetupflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원목록조회
     */
    @Transactional(readOnly = true)
    public List<UserListResponse> findUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserListResponse::new)
                .toList();
    }

    /**
     * 회원단건조회 TODO 에러처리
     * useresponse로 반환하면 재활용이 어렵다.. -> 그래서 일단은 entity를 반환
     */
    @Transactional(readOnly = true)
    public UserResponse findUser(Long id){
        return UserResponse.from(getUser(id));
    }

    /**
     * 회원가입 TODO 에러처리
     */
    @Transactional
    public CreateUserResponse join(String name, String email, Address address) {
        User user = User.createUser(name, email, address);
        userRepository.save(user);
        return CreateUserResponse.from(user);
    }

    /**
     * 회원정보수정
     */
    @Transactional
    public UpdateUserResponse updateUser(Long id, String email, Address address) {
        User user = getUser(id);
        user.updateUserProfile(email, address);
        return UpdateUserResponse.from(user);
    }

    /**
     * 회원탈퇴
     */
    @Transactional
    public void withdrawUser(Long id) {
        User user = getUser(id);
        user.changeStatusToDeleted();
    }

    //==== 기타메서드 =====//
    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(""));
    }
}
