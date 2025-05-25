package com.example.sports.websocket;

import com.example.sports.security.AppUserDetails;
import com.example.sports.services.AuthenticationService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    private final AuthenticationService authenticationService;

    public WebSocketAuthInterceptor(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {

        if(request instanceof ServletServerHttpRequest servletServerHttpRequest) {
            String token = servletServerHttpRequest.getServletRequest().getParameter("token");

            UserDetails userDetails = authenticationService.validateToken(token);

            if(token != null && userDetails !=null) {
                if(userDetails instanceof AppUserDetails) {
                    attributes.put("userId", ((AppUserDetails) userDetails).getId());
                    attributes.put("authorities", userDetails.getAuthorities());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
