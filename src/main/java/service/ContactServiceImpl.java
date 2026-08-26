package service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import domain.Contact;
import exception.MyAgendaException;
import repository.ContactArrayListRepository;
import repository.ContactHashMapRepository;

public class ContactServiceImpl implements ContactService {
    private ContactHashMapRepository hashMapContactRepository;
    private ContactArrayListRepository arrayListContactRepository;

    ContactServiceImpl(ContactHashMapRepository hashMapContactRepository, ContactArrayListRepository arrayListContactRepository){

        this.hashMapContactRepository = hashMapContactRepository;
        this.arrayListContactRepository = arrayListContactRepository;

    }

    @Override
    public Contact findContactById(UUID id) {

        Contact resultContact = arrayListContactRepository.findContactById(id); 

        if ( resultContact == null) {
            
            throw new MyAgendaException("Contact ID: " + id + " Not found. Please provide a valide ID.");
        }
    
        return resultContact;
    }

    @Override
    public List<Contact> findContactsByName(String name) {
        
        if ( name.isBlank() || name.isEmpty() ) {
            throw new MyAgendaException("Name cannot be Empty or Blank. Please provide a valide Name.");
        }

        return arrayListContactRepository.findContactsByName(name);

    }

    @Override
    public Contact registerContact(Contact contact) {
        
        this.validateContact(contact);

        arrayListContactRepository.addContact(contact);

        return null;
    }

    @Override
    public void deleteContact(UUID id) {
        
        Contact contactToBeDeleted = findContactById(id);

        arrayListContactRepository.deleteContact(contactToBeDeleted);
        
    }
    
    private void validateContact(Contact contact){

        if (this.isContactAlreadyRegistered(contact)) {
            throw new MyAgendaException("Contact already exists");
        }

        if (contact.getId() == null){
            throw new MyAgendaException("Contact ID cannot be null");
        };

        if (contact.getName() == null || contact.getName().isEmpty() || contact.getName().isBlank()) {
            throw new MyAgendaException("Contact name cannot be null, empty or have only white spaces.");
        };

        if (contact.getEmail() == null || contact.getEmail().isEmpty() || contact.getEmail().isBlank()) {
            throw new MyAgendaException("Contact email cannot be null, empty or have only white spaces.");
        };

        if (contact.getPhone() == null || contact.getPhone().isEmpty() || contact.getPhone().isBlank()) {
            throw new MyAgendaException("Contact phone number cannot be null, empty or have only white spaces.");
        };

        if (contact.getDepartment() == null || contact.getDepartment().isEmpty() || contact.getDepartment().isBlank()) {
            throw new MyAgendaException("Contact department cannot be null, empty or have only white spaces.");
        };

        if (contact.getRole() == null || contact.getRole().isEmpty() || contact.getRole().isBlank()) {
            throw new MyAgendaException("Contact role cannot be null, empty or have only white spaces.");
        };

    }

    private Boolean isContactAlreadyRegistered(Contact contact){

        Contact searchResult = arrayListContactRepository.findContactById(contact.getId());

        if ( searchResult != null ) {
            return true;
        }

        return false;

    }
    


}
