package service;

import java.util.List;
import java.util.UUID;

import domain.Contact;

public interface ContactService {
    
    public Contact findContactById(UUID id);
    public List<Contact> findContactsByName(String name);
    public Contact registerContact(Contact contact);
    public Void deleteContact(UUID id);

}
