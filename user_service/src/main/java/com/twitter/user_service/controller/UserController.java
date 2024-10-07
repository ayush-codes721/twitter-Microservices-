package com.twitter.user_service.controller;

import com.twitter.user_service.dto.UserDTO;
import com.twitter.user_service.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserController {
    private final UserService userService;


    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }


}
