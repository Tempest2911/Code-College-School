package org.example.j6backendjava.Config;

import org.example.j6backendjava.enity.ChatMessage;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketListener {

    private SimpMessageSendingOperations messagingTemplate;

    public WebSocketListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void disConnected(SessionConnectedEvent event) {
        ChatMessage msg = new ChatMessage();
        msg.setType(ChatMessage.MessageType.LEAVE);
        msg.setSender("poly");
        messagingTemplate.convertAndSend("/topic/chat", msg);
    }
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        String sessionID = event.getSessionId();
        System.out.println("Disconnected from " + sessionID);
    }
}