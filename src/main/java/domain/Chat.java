package domain;

import java.util.List;
import java.util.UUID;

public class Chat {
    
    private UUID id;
    private List<Contact> participants;
    private List<Message> messages;

    public Chat(List<Contact> participants, List<Message> messages) {
        this.id = UUID.randomUUID();
        this.participants = participants;
        this.messages = messages;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public List<Contact> getParticipants() {
        return participants;
    }
    public void setParticipants(List<Contact> participants) {
        this.participants = participants;
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    

}
