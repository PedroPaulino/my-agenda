package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Message {
    
    private UUID id;
    private String content;
    private Contact sender;
    private Chat chat;
    private String sentAt;
    private DateTimeFormatter dtFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Message(String content, Contact sender, Chat chat) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.sender = sender;
        this.chat = chat;
        this.sentAt = LocalDateTime.now().format(dtFormater);
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Contact getSender() {
        return sender;
    }
    public void setSender(Contact sender) {
        this.sender = sender;
    }
    public Chat getChat() {
        return chat;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public String getSentAt() {
        return sentAt;
    }
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt.format(dtFormater);
    }

}
