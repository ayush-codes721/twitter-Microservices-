package com.twitter.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingTweetServiceFilter extends AbstractGatewayFilterFactory<LoggingTweetServiceFilter.Config> {


    public LoggingTweetServiceFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            log.info("this is a pre route specific twitter filter  url {}", exchange.getRequest().getURI());

            return chain.filter(exchange);
        });
    }

    static class Config {

    }
}
