package com.twitter.tweet_service.Cilents;


import com.twitter.tweet_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service",url = "http://localhost:5014/auth")
public interface UserClient {

    @GetMapping("/user/profile/{id}")
    UserDTO getUserById(@PathVariable Long id);



}
