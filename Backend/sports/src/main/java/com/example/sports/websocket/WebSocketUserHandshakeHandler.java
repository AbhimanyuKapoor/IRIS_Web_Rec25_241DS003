package com.example.sports.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Component
public class WebSocketUserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) {
        UUID userId = (UUID) attributes.get("userId");
        Collection<? extends GrantedAuthority> authorities =
                (Collection<? extends GrantedAuthority>) attributes.get("authorities");

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }
}
