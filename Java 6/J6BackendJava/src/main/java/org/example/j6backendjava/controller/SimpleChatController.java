package org.example.j6backendjava.controller;

import org.example.j6backendjava.enity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleChatController {
    @MessageMapping("simple-chat")
    @SendTo("/topic/simple-chat")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
            return message;
    }
}
