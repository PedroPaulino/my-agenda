package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Contact;

public class ContactRepositoryBenchmark {

    public static void main(String[] args) {
        
        int[] sizes = {
            100,
            1_000,
            10_000,
            100_000,
            1_000_000
        };

        int repetitions = 100;

        System.out.println(" SEARCH BY ID RESULTS ");

        for (int size: sizes){

            ContactArrayListRepository contactArrayListRepository = new ContactArrayListRepository();

            ContactHashMapRepository contactHashMapRepository = new ContactHashMapRepository();

            List<Contact> contacts = createContacts(size);

            for(Contact contact : contacts){
                contactArrayListRepository.addContact(contact);
                contactHashMapRepository.addContact(contact);
            }

            Contact target = contacts.get(contacts.size() - 1);

            long arrayListTime = measureArrayListById(
                contactArrayListRepository,
                target.getId(),
                repetitions
            );

            long hashMapTime = measureHashMapById(
                contactHashMapRepository,
                target.getId(),
                repetitions
            );

            System.out.println(
                "N = \t" + size +
                "\t\t\t| ArrayList = " + arrayListTime + " ns" +
                "\t\t| HashMap = " + hashMapTime + " ns"
            );

        }

        System.out.println(" SEARCH BY NAME RESULTS ");

        for(int size : sizes){

            
            ContactArrayListRepository contactArrayListRepository = new ContactArrayListRepository();

            ContactHashMapRepository contactHashMapRepository = new ContactHashMapRepository();

            List<Contact> contacts = createContacts(size);

            for(Contact contact : contacts){
                contactArrayListRepository.addContact(contact);
                contactHashMapRepository.addContact(contact);
            }

            Contact target = contacts.get(contacts.size() - 1);
              
            String targetName = target.getName();

            long arrayListTimeName = measureArrayListByName(
                contactArrayListRepository,
                targetName,
                repetitions
            );

            long hashMapTimeName = measureHashMapByName(
                contactHashMapRepository,
                targetName,
                repetitions
            );

            System.out.println(
                "N = \t" + size +
                "\t\t\t| ArrayList = " + arrayListTimeName + " ns" +
                "\t\t| HashMap = " + hashMapTimeName + " ns"
            );
        }


    }

    public static List<Contact> createContacts(int size){
        List<Contact> contacts = new ArrayList<>();

        for(int numContact = 0; numContact <= size; numContact++){

            Contact contact = new Contact(
                "contact_number_" + numContact,
                "contact@" + numContact + ".com",
                "+00000000000",
                "P&D",
                "Contact_Number_" + numContact,
                "Number Street, Hood, Country",
                "Hi I'm Contact Number " + numContact,
                null,
                null
            );

            contacts.add(contact);
        }

        return contacts;

    }

    public static Long measureArrayListById(ContactArrayListRepository repository, UUID contactId, int repetitions){
        Contact result = null;

        long start = System.nanoTime();

        for(int i = 0; i < repetitions; i++){
            result = repository.findContactById(contactId);
        }

        long end = System.nanoTime();

        if (result == null) {
            throw new RuntimeException("Unexpected result");
        }

        return end - start;
    }
    public static Long measureHashMapById(ContactHashMapRepository repository, UUID contactId, int repetitions){
        Contact result = null;

        long start = System.nanoTime();

        for(int i = 0; i < repetitions; i++){
            result = repository.findContactById(contactId);
        }

        long end = System.nanoTime();

        if (result == null) {
            throw new RuntimeException("Unexpected result");
        }

        return end - start;
    }

    public static Long measureArrayListByName(ContactArrayListRepository repository, String contactName, int repetitions){
        List<Contact> result = null;

        long start = System.nanoTime();

        for(int i = 0; i < repetitions; i++){
            result = repository.findContactsByName(contactName);
        }

        long end = System.nanoTime();

        if (result == null) {
            throw new RuntimeException("Unexpected result");
        }

        return end - start;
    }

    public static Long measureHashMapByName(ContactHashMapRepository repository, String contactName, int repetitions){
        List<Contact> result = null;

        long start = System.nanoTime();

        for(int i = 0; i < repetitions; i++){
            result = repository.findContactsByName(contactName);
        }

        long end = System.nanoTime();

        if (result == null) {
            throw new RuntimeException("Unexpected result");
        }

        return end - start;
    }


}