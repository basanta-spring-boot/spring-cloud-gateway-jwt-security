package com.javatechie.security;

import com.javatechie.dto.ErrorResponseDto;
import com.javatechie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import org.springframework.util.SerializationUtils;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


    public AuthenticationFilter() {
        super(Config.class);
    }

    @Autowired
    private  RouterValidator routerValidator;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routerValidator.isSecured.test(exchange.getRequest()) ) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorisation Header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                }
                catch (Exception ex) {
                    System.out.println("Error Validating Authentication Header"+ ex.getMessage());
                    List<String> details = new ArrayList<>();
                    details.add(ex.getLocalizedMessage());
                    ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, exchange.getRequest().getURI().toString());
                    ServerHttpResponse response = exchange.getResponse();

                    byte[] bytes = SerializationUtils.serialize(error);

                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                    response.writeWith(Flux.just(buffer));
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
