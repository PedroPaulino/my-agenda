package service;

import java.util.UUID;

import domain.Chat;
import domain.Contact;
import domain.Group;

public interface ChatService {
    
    public Chat findChatById(UUID id);
    public Chat createPrivateChat(Contact user1, Contact user2);
    public Chat createGroupChat(Group group);
    public void deleteChat(Chat chat);
    public Boolean isParticipant(Chat chat, Contact sender);

}
