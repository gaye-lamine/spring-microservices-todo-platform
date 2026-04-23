package com.example.gateway.security;

import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        HttpMethod method = request.getMethod();

        log.info("JwtAuthenticationFilter incoming request: method={} path={} headers={}", method, path, request.getHeaders().keySet());

        if (path.startsWith("/api/auth")
                || path.startsWith("/api/notifications")
                || (path.startsWith("/api/users") && HttpMethod.POST.equals(method))) {
            log.info("[GATEWAY] PUBLIC route — forwarding {} {} to chain", method, path);
            return chain.filter(exchange)
                .doOnSuccess(v -> log.info("[GATEWAY] chain.filter() completed for {} {}", method, path))
                .doOnError(e -> log.error("[GATEWAY] chain.filter() ERROR for {} {}: {}", method, path, e.getMessage()));
        }

        log.info("[GATEWAY] PROTECTED route — checking JWT for {} {}", method, path);

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("[GATEWAY] Missing/invalid Authorization header for {} {}", method, path);
            return onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);
        log.info("[GATEWAY] Validating JWT token (first 20 chars): {}", token.substring(0, Math.min(20, token.length())));

        if (!jwtUtils.validateToken(token)) {
            log.warn("[GATEWAY] Invalid JWT token for {} {}", method, path);
            return onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }

        String username = jwtUtils.getUsernameFromToken(token);
        log.info("[GATEWAY] JWT valid — user={}, forwarding {} {}", username, method, path);
        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("X-Auth-User", username)
                .build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build())
            .doOnSuccess(v -> log.info("[GATEWAY] chain.filter() completed for {} {}", method, path))
            .doOnError(e -> log.error("[GATEWAY] chain.filter() ERROR for {} {}: {}", method, path, e.getMessage()));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        log.error("[GATEWAY] onError: {} -> {}", err, httpStatus);
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}