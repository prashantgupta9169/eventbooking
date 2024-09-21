package com.eventbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.data.UserCreationRequest;
import com.eventbooking.data.UserResponse;
import com.eventbooking.service.UserService;

@RestController
@RequestMapping("/users")
public class UserApiResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createEvent(
            @RequestBody @Validated UserCreationRequest userCreationRequest) {
        UserResponse userResponse = userService.createUser(userCreationRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    @RequestMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserDetailsById(@PathVariable Long userId) {
        UserResponse userResponse = userService.getUserDetails(userId);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping
    @RequestMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String response = userService.deleteUser(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @RequestMapping("/update/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Long userId,
            @RequestBody UserCreationRequest userCreationRequest) {
        userService.updateUserDetails(userId, userCreationRequest);
        return ResponseEntity.ok("Updated");
    }

}
