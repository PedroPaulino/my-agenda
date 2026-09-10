package domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Chat {
    
    private UUID id;
    private Set<Contact> participants;
    private ChatType chatType;
    private List<Message> messages;

    public Chat(Set<Contact> participants, List<Message> messages, ChatType chatType) {
        this.id = UUID.randomUUID();
        this.participants = participants;
        this.messages = messages;
        this.chatType = chatType;
    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Set<Contact> getParticipants() {
        return participants;
    }
    public void setParticipants(Set<Contact> participants) {
        this.participants = participants;
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    

}
