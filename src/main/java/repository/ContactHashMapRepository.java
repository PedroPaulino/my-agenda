package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import domain.Contact;

public class ContactHashMapRepository {
    private HashMap<UUID, Contact> mockContactDb;
    
    public  ContactHashMapRepository(){
        this.mockContactDb = new HashMap<UUID, Contact>();
    }

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

    public void addContact(Contact contact) {
        
        mockContactDb.put(contact.getId(), contact);

    }
     public void deleteContact(Contact contact){
        mockContactDb.remove(contact.getId(), contact);
     }

}
