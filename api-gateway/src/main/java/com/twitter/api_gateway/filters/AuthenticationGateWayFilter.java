package com.twitter.api_gateway.filters;

import com.twitter.api_gateway.Clinets.AuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationGateWayFilter extends AbstractGatewayFilterFactory<AuthenticationGateWayFilter.Config> {

    private final AuthClient authClient;

    @Autowired
    public AuthenticationGateWayFilter( @Lazy AuthClient authClient) {

        super(Config.class);
        this.authClient = authClient;

    }

    @Override
    public GatewayFilter apply(Config config) {


        return (exchange, chain) -> {

            String requestHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (requestHeader == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = requestHeader.split("Bearer")[1].trim();
            boolean isValid = authClient.validateToken(token);
            if (!isValid) {


                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            Long userId = authClient.getUserIdFromToken(token);

            //mutating the request
            exchange.getRequest()
                    .mutate()
                    .header("X-User_Id", userId.toString())
                    .build();

            log.info("Request Headers after mutation: {}", exchange.getRequest().getHeaders());


            return chain.filter(exchange);
        };
    }

    static class Config {

    }
}
