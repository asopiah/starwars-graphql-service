package com.sovtech.starwars.graphql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author asopia
 */
@Slf4j
public class LoggingFilter implements ExchangeFilterFunction {
    private final AtomicLong counter = new AtomicLong(0);

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        long startTime = System.currentTimeMillis();
        long requestId = counter.incrementAndGet();

        // Log the request
        log.info("requestID=[{}] | requestMethod={} | requestUrl={}", requestId, request.method(), request.url());

        return next.exchange(request).doOnSuccess(response -> {
            // Log the response
            log.info("requestID=[{}] | responseCode={} | responseTime={}ms", requestId, response.statusCode(), System.currentTimeMillis() - startTime);
        });
    }
}