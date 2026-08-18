package service;

import java.util.UUID;

import domain.Chat;
import domain.Contact;
import domain.Group;
import repository.ChatRepository;

public class ChatServiceImpl implements ChatService {
    private ChatRepository repository;

    ChatServiceImpl(ChatRepository repository){
        this.repository = repository;
    }

    @Override
    public Chat createGroupChat(Group group) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chat createPrivateChat(Contact user1, Contact user2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteChat(Chat chat) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Chat findChatById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean isParticipant(Chat chat, Contact sender) {
        // TODO Auto-generated method stub
        return null;
    }
    
    

}
