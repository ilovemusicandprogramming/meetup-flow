package com.example.meetupflow.service;

import com.example.meetupflow.domain.User;
import com.example.meetupflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }
}
