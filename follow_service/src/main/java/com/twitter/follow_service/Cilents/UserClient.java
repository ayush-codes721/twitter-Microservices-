package com.twitter.follow_service.Cilents;

import com.twitter.follow_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "http://localhost:5010")
public interface UserClient {

    @GetMapping("/user/profile/{id}")
    UserDTO getUserById(@PathVariable Long id);



}