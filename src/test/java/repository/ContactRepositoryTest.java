package repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Contact;

public class ContactRepositoryTest {
    private ContactArrayListRepository arrListRepo;
    private ContactHashMapRepository hashMapRepo;

    @BeforeEach 
    void setup(){
        arrListRepo = new ContactArrayListRepository();
        hashMapRepo = new ContactHashMapRepository();
    }

    @Test
    void shouldFindContactById(){
        // Arrange
        Contact contact = new Contact(
                "Test",
                "test@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );
        arrListRepo.addContact(contact);
        hashMapRepo.addContact(contact);
        UUID contactId = contact.getId();

        // Act
        Contact arrResponse = arrListRepo.findContactById(contactId);
        Contact hashResponse = hashMapRepo.findContactById(contactId);

        // Assert
        assertNotNull(arrResponse);
        assertNotNull(hashResponse);
        assertEquals(contactId, arrResponse.getId());
        assertEquals(contactId, hashResponse.getId());

    }

    @Test 
    void shouldReturnNullWhenContatIdIsNotFound(){
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Contact arrResponse = arrListRepo.findContactById(randomId);
        Contact hashResponse = hashMapRepo.findContactById(randomId);

        // Assert
        assertNull(arrResponse);
        assertNull(hashResponse);

    }

    @Test
    void shouldReturnContactsByName(){
        // Arrange
        String contactName = "Test";

        Contact contact1 = new Contact(
                "Test 1",
                "test1@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );

        Contact contact2 = new Contact(
                "Test 2",
                "test2@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );

        Contact contact3 = new Contact(
                "Test 3",
                "test3@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );
        arrListRepo.addContact(contact1);
        arrListRepo.addContact(contact2);
        arrListRepo.addContact(contact3);
        hashMapRepo.addContact(contact1);
        hashMapRepo.addContact(contact2);
        hashMapRepo.addContact(contact3);

        // Act
        List<Contact> arrResponse = arrListRepo.findContactsByName(contactName);
        List<Contact> hashResponse = hashMapRepo.findContactsByName(contactName);

        // Assert
        assertNotNull(arrResponse);
        assertNotNull(hashResponse);
        assertEquals(arrResponse.size(), 3);
        assertEquals(hashResponse.size(), 3);
        assertTrue(
            arrResponse.stream().allMatch( contact -> contact.getName().contains(contactName)), "Not all contacts contains " + contactName
        );

        assertTrue(
            hashResponse.stream().allMatch( contact -> contact.getName().contains(contactName)), "Not all contacts contains " + contactName
        );

    }

    @Test 
    void shouldReturnEmptyWhenContactNameIsNotFound(){
        // Arrange
        String contactName = "Test";
        
        // Act
        List<Contact> arrResponse = arrListRepo.findContactsByName(contactName);
        List<Contact> hashResponse = hashMapRepo.findContactsByName(contactName);

        // Assert
        assertTrue(arrResponse.isEmpty());
        assertTrue(hashResponse.isEmpty());

    }

    @Test 
    void shouldAddContact(){
        // Arrange
        Contact contact1 = new Contact(
                "Test 1",
                "test1@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );

        // Act
        arrListRepo.addContact(contact1);
        hashMapRepo.addContact(contact1);
        Contact arrResponse = arrListRepo.findContactById(contact1.getId());
        Contact hashResponse = hashMapRepo.findContactById(contact1.getId());

        // Assert
        assertNotNull(arrResponse);
        assertNotNull(hashResponse);
        assertEquals(arrResponse.getId(), contact1.getId());
        assertEquals(hashResponse.getId(), contact1.getId());

    }

    @Test 
    void shouldDeleteContact(){
        // Arrange
         Contact contact1 = new Contact(
                "Test 1",
                "test1@test.com",
                "+11 101010101",
                "P&D",
                "Tester",
                "Number Street, Hood, Country",
                "Hi I'm Test",
                null,
                null
        );
        arrListRepo.addContact(contact1);
        hashMapRepo.addContact(contact1);

        Contact arrResponse = arrListRepo.findContactById(contact1.getId());
        Contact hashResponse = hashMapRepo.findContactById(contact1.getId());
        assertNotNull(arrResponse);
        assertNotNull(hashResponse);

        // Act
        arrListRepo.deleteContact(contact1);
        hashMapRepo.deleteContact(contact1);

        // Assert
        assertNull(arrListRepo.findContactById(contact1.getId()));
        assertNull(hashMapRepo.findContactById(contact1.getId()));

    }

}
