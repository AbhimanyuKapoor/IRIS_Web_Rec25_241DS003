package com.example.sports.config;

import com.example.sports.websocket.WebSocketAuthInterceptor;
import com.example.sports.websocket.WebSocketUserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthInterceptor webSocketAuthInterceptor;

    public WebSocketConfig(WebSocketAuthInterceptor webSocketAuthInterceptor) {
        this.webSocketAuthInterceptor = webSocketAuthInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue", "/topic"); // internal broker for destinations
        config.setApplicationDestinationPrefixes("/app"); // for messages sent from client to server
        config.setUserDestinationPrefix("/user"); // enables /user/queue/...
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Url Endpoint for establishing handshake
                .addInterceptors(webSocketAuthInterceptor)
                .setHandshakeHandler(new WebSocketUserHandshakeHandler())
                .setAllowedOrigins("http://127.0.0.1:5501") // Port at which my client runs (Change * later)
                .withSockJS(); // Fall back in case browser doesn't support web socket
    }
}
