package org.example.j6backendjava.Config;

import org.example.j6backendjava.enity.ChatMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
public class WebSocketListener {
    private SimpMessageSendingOperations messagingTemplate;
    public WebSocketListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @EventListener
    public void disConnected(SessionDisconnectEvent event) {
        ChatMessage message = new ChatMessage();
        message.setType(ChatMessage.MessageType.LEAVE);
        message.setSender("poly");
        messagingTemplate.convertAndSend("/topic/leave", message);
    }
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        System.out.println("Mat ket noi toi server: " + sessionId);
    }
}
