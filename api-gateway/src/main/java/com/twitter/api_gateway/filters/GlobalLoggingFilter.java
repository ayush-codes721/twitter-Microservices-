package com.twitter.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalLoggingFilter  implements GlobalFilter , Ordered {
    private static final Logger log = LoggerFactory.getLogger(GlobalLoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //pre-filter
        log.info("this is global pre filter...{}",exchange.getRequest().getURI());

       return chain.filter(exchange);

//        return chain.filter(exchange).then(Mono.fromRunnable(()->{
//
//            //post filter
//            log.info("this is global post filter {}",exchange.getResponse().getStatusCode());  // like promise when request completes then log runs here
//        }));
    }

    @Override
    public int getOrder() {
        return 5; // changing the order of filter it will be 5
    }
}
