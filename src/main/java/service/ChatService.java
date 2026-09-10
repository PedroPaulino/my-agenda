package service;

import java.util.List;
import java.util.UUID;

import domain.Chat;
import domain.Contact;
import domain.Group;
import domain.Message;

public interface ChatService {
    
    public Chat findChatById(UUID id);
    public Chat createPrivateChat(Contact user1, Contact user2);
    public Chat createGroupChat(Group group);
    public void deleteChat(Chat chat);
    public Boolean isParticipant(Chat chat, Contact sender);
    public Message findMessageById(Chat chat, UUID id);
    public List<Message> getMessages(Chat chat);
    public Message sendMessage(Chat chat, Contact sender, String content);

}
