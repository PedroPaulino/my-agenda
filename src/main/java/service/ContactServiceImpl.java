package service;

import java.util.List;
import java.util.UUID;

import domain.Contact;
import repository.ContactArrayListRepository;
import repository.ContactHashMapRepository;

public class ContactServiceImpl implements ContactService {
    private ContactHashMapRepository hashMapRepository;
    private ContactArrayListRepository arrayListRepository;

    ContactServiceImpl(ContactHashMapRepository hashMapRepository, ContactArrayListRepository arrayListRepository){

        this.hashMapRepository = hashMapRepository;
        this.arrayListRepository = arrayListRepository;

    }

    @Override
    public Void deleteContact(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Contact findContactById(UUID id) {
        // TODO Auto-generated method stub


        return null;
    }

    @Override
    public List<Contact> findContactsByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Contact registerContact(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }
    
    


}
