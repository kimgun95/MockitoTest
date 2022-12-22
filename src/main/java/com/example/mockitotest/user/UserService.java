package com.example.mockitotest.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserResponse signUp(SignUpRequest signUpRequest) {
        return new UserResponse(userRepository.save(signUpRequest.toEntity()));
    }
}
