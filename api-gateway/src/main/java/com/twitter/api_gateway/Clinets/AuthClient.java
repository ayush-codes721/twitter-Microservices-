package com.twitter.api_gateway.Clinets;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:5014/auth", name = "auth-service")
public interface AuthClient {

    @GetMapping("/validateToken")
    boolean validateToken(@RequestParam String token);

    @GetMapping("/jwt/getUserId/{token}")
    Long getUserIdFromToken(@PathVariable  String token);
}
