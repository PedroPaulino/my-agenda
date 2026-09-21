package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import domain.Chat;
import domain.ChatType;
import domain.Contact;
import domain.Group;
import domain.Message;
import exception.MyAgendaException;
import repository.ChatRepository;

public class ChatServiceImpl implements ChatService {
    private ChatRepository repository;

    public ChatServiceImpl(ChatRepository repository){
        this.repository = repository;
    }

    @Override
    public Chat createGroupChat(Contact owner) {

        List<Message> messages = new ArrayList<Message>();

        Set<Contact> participants = new HashSet<Contact>();
        participants.add(owner);

        Chat groupChat = new Chat(participants, messages, ChatType.GROUP);

        repository.saveChat(groupChat);

        return groupChat;
    }

    @Override
    public Chat createPrivateChat(Contact user1, Contact user2) {
        
        Set<Contact> participants = new HashSet<Contact>();
        participants.add(user1);
        participants.add(user2);
        List<Message> messages = new ArrayList<Message>();

        Chat privateChat = new Chat(participants, messages, ChatType.PRIVATE);

        repository.saveChat(privateChat);

        return privateChat;
    }

    @Override
    public void deleteChat(Chat chat) {
        repository.deleteChat(chat);
        
    }

    @Override
    public Chat findChatById(UUID id) {
        
        Chat resultChat = repository.findChatById(id);

        if (resultChat == null) {
            
            throw new MyAgendaException("Chat ID: " + id + " Not found. Please provide a valid ID");

        }

        return resultChat; 
    }

    @Override
    public Boolean isParticipant(Chat chat, Contact sender) {
        
        if (chat.getParticipants().contains(sender)) 
            return true;

        return false;
    }

    @Override
    public Message findMessageById(Chat chat, UUID id) {
        
        return chat.getMessages().stream()
            .filter(message -> message.getId().equals(id))
            .findFirst()
            .orElse(null);

    }

    @Override
    public List<Message> getMessages(Chat chat) {
        return chat.getMessages();
    }

    @Override
    public Message sendMessage(Chat chat, Contact sender, String content) {
        
        if ( chat == null ) {
            throw new MyAgendaException("Chat object was null. Please provide a valid chat");
        }

        findChatById(chat.getId());

        if (content == null) {
            throw new  MyAgendaException("Message content cannot be null");
        }

        if ( !isParticipant(chat, sender) ){
            throw new MyAgendaException("Sender: " + sender.getName() + " is not participant of Chat: " + chat.getId());
        };

        Message message = new Message(content, sender, chat);

        chat.getMessages().add(message);

        return message;
    }
    
    

}
