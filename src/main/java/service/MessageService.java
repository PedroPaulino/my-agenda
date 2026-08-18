package service;

import java.util.List;
import java.util.UUID;

import domain.Chat;
import domain.Contact;
import domain.Message;

public interface MessageService {

    public Message findMessageById(Chat chat, UUID id);
    public Message sendMessage(Chat chat, Contact sender, String content);
    public List<Message> getMessages();

}
