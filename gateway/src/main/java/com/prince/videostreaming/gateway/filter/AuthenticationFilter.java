package com.prince.videostreaming.gateway.filter;

import com.prince.videostreaming.gateway.feign.FeignClientAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final FeignClientAuthService feignClientAuthService;

    @Autowired
    public AuthenticationFilter(RouteValidator routeValidator, @Lazy FeignClientAuthService feignClientAuthService) {
        super(Config.class);
        validator = routeValidator;
        this.feignClientAuthService = feignClientAuthService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    ResponseEntity<String> res = feignClientAuthService.validateToken(authHeader);
                    if (res.getStatusCode() != HttpStatusCode.valueOf(200)) {
                        System.out.println("invalid access...!");
                        throw new RuntimeException("un authorized access to application");
                    }
                } catch (Exception e) {
                    System.out.println("Error from AuthenticaionFilter... !" + e.getMessage());
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}