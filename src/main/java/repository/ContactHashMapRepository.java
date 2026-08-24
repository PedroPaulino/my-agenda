package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import domain.Contact;

public class ContactHashMapRepository {
    private HashMap<UUID, Contact> mockContactDb;
    
    public Contact findContactById(UUID id){

        if ( mockContactDb.containsKey(id) ){
            return mockContactDb.get(id);
        }

        return null;

    }

     public List<Contact> findContactsByName(String contactName) {
        List<Contact> result = new ArrayList<>();

        mockContactDb.forEach(
            (id, contact) -> { 
                if ( contact.getName().contains(contactName) ){
                    result.add(contact);
                };
            }
        );

        return result;
     }

    public Contact addContact(Contact contact) {

        try {
            mockContactDb.put(contact.getId(), contact);
            return contact;
        } catch (Exception e) {
            return null;
        }

    }
     public Boolean deleteContact(Contact contact){
        return mockContactDb.remove(contact.getId(), contact);
     }

}
