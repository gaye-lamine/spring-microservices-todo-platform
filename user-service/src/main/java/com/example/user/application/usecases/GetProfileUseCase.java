package com.example.user.application.usecases;

import com.example.user.domain.entities.User;
import com.example.user.domain.repository.UserRepository;
import com.example.user.application.dto.UserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class GetProfileUseCase {
    private final UserRepository userRepository;

    public GetProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileResponse execute(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return new UserProfileResponse(user);
    }
}