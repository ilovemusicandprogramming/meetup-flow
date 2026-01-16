package com.example.meetupflow.service;

import com.example.meetupflow.domain.Address;
import com.example.meetupflow.domain.User;
import com.example.meetupflow.dto.user.UpdateUserRequest;
import com.example.meetupflow.repository.UserRepository;
import jakarta.validation.Valid;
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
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    /**
     * 회원단건조회
     */
    @Transactional(readOnly = true)
    public User findOne(Long id){
        return userRepository.findById(id).get();
    }

    /**
     * 회원가입
     */
    @Transactional
    public Long join(String name, String email, Address address) {
        User user = User.createUser(name, email, address);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 회원정보수정
     */
    @Transactional
    public void updateUser(Long id, String email, Address address) {
        User user = userRepository.findById(id).get();
        user.updateUserProfile(email, address);
    }
}
