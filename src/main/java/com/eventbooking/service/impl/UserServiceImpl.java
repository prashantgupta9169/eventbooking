package com.eventbooking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eventbooking.data.UserCreationRequest;
import com.eventbooking.data.UserResponse;
import com.eventbooking.domain.User;
import com.eventbooking.domain.UserRepository;
import com.eventbooking.exception.BadRequestException;
import com.eventbooking.exception.NotFoundException;
import com.eventbooking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByEmail(userCreationRequest.getEmail())) {
            throw new BadRequestException("User already exists with the given email");
        }
        User user = User.builder()
                .name(userCreationRequest.getName())
                .email(userCreationRequest.getEmail())
                .build();
        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public UserResponse getUserDetails(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with id: " + userId.toString());
        }
        User user = userOptional.get();

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public String deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with id: " + userId.toString());
        }
        userRepository.delete(userOptional.get());
        return "User deleted successfully";
    }

    @Override
    public UserResponse updateUserDetails(Long userId, UserCreationRequest userCreationRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found with id: " + userId.toString());
        }
        User user = userOptional.get();
        user.setName(userCreationRequest.getName());
        user.setEmail(userCreationRequest.getEmail());
        User updatedUser = userRepository.save(user);
        return UserResponse.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .build();
    }

}
