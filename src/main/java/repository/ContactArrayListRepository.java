package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Contact;

public class ContactArrayListRepository {
    private ArrayList<Contact> mockContactDb;

    public Contact findContactById(UUID id) {

        return mockContactDb.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public List<Contact> findContactsByName(String contactName) {
        List<Contact> result = new ArrayList<>();

        for (Contact contact : mockContactDb) {

            if (contact.getName().contains(contactName)) {
                result.add(contact);
            }
        }

        return result;
    }

    public void addContact(Contact contact) {

        mockContactDb.add(contact);
    }

    public void deleteContact(Contact contact) {

        mockContactDb.remove(contact);
        
    }

}
