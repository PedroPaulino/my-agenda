package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Message;

public class MessageRepository {
    private ArrayList<Message> mockMessageDb;

    public Message findMessageById(UUID id) {

        return mockMessageDb.stream()
            .filter(message -> message.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public void saveMessage(Message message){
        mockMessageDb.add(message);
    }

    public List<Message> getAll(){

        return mockMessageDb;
    }

}
