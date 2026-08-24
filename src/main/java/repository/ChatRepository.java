package repository;

import java.util.List;
import java.util.UUID;

import domain.Chat;

public class ChatRepository {
    private List<Chat> mockChatDb;

    public Chat findChatById(UUID id){

        return mockChatDb.stream()
        .filter( chat -> chat.getId().equals(id))
        .findFirst()
        .orElse(null);

    }

    public Chat saveChat(Chat chat){

        try{
            mockChatDb.add(chat);
            return chat;
        } catch (Exception e){
            return null;
        }

    }

    public boolean deleteChat(Chat chat){
        return mockChatDb.remove(chat);
    }
    
}
