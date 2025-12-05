package org.example.j6backendjava.enity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

@Builder.Default
    private MessageType type = MessageType.CHAT;
    private String sender;
    private String content;
}
