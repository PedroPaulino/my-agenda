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

    public void saveChat(Chat chat){
        mockChatDb.add(chat);

    }

    public void deleteChat(Chat chat){
        mockChatDb.remove(chat);
    }
    
}
