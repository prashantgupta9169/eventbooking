package com.eventbooking.service;

import com.eventbooking.data.UserCreationRequest;
import com.eventbooking.data.UserResponse;

public interface UserService {

    UserResponse createUser(UserCreationRequest UserCreationRequest);

    UserResponse getUserDetails(Long userId);

    String deleteUser(Long userId);

    UserResponse updateUserDetails(Long userId, UserCreationRequest UserCreationRequest);
}
