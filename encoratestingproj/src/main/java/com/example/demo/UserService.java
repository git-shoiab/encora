package com.example.demo;

import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserNameById(Long id) {
        return userRepository.findById(id)
                .map(User::getUsername)
                .orElse("Unknown");
    }
}


