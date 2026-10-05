import java.util.List;

import domain.Chat;
import domain.Contact;
import domain.Group;
import repository.ChatRepository;
import repository.ContactArrayListRepository;
import repository.ContactHashMapRepository;
import repository.GroupRepository;
import service.ChatServiceImpl;
import service.ContactServiceImpl;
import service.GroupServiceImpl;

public class Application {

    public static void main(String[] args) {

        /**
         * Private Chat Flow
         */
        ChatRepository chatRepository = new ChatRepository();
        ChatServiceImpl chatService = new ChatServiceImpl(chatRepository);

        ContactArrayListRepository contactArrayListRepository = new ContactArrayListRepository();
        ContactHashMapRepository contactHashMapRepository = new ContactHashMapRepository();

        ContactServiceImpl contactService = new ContactServiceImpl(contactHashMapRepository,
                contactArrayListRepository);

        System.out.println("------------------ CONTACT --------------");
        Contact contact1 = new Contact(
                "John",
                "john_doe@test.com",
                "+55 55555555",
                "P&D",
                "Software Engineer",
                "99 Street, Hood, Country",
                "Hi I'm John Doe",
                null,
                null);

        Contact contact1_alt = new Contact(
                "John Henry",
                "john_henry@test.com",
                "+66 666666",
                "P&D",
                "Software Engineer",
                "79 Street, Hood, Country",
                "Hi I'm John Henry",
                null,
                null);

        Contact contact2 = new Contact(
                "Mark",
                "mark_doe@test.com",
                "+99 999999",
                "P&D",
                "Software Engineer II",
                "1040 Street, Hood, Country",
                "Hi I'm Mark Doe",
                null,
                null);

        Contact contact3 = new Contact(
                "Hellen",
                "hellen_doe@test.com",
                "+23 23232323",
                "P&D",
                "Software Engineer",
                "9 Street, Hood, Country",
                "Hi I'm Hellen Doe",
                null,
                null);

        Contact contact4 = new Contact(
                "Mary",
                "mary_doe@test.com",
                "+78 787878",
                "P&D",
                "Software Engineer II",
                "1140 Street, Hood, Country",
                "Hi I'm Mary Doe",
                null,
                null);
        

        Contact registeredContact1 = contactService.registerContact(contact1);
        Contact registeredContact1_alt = contactService.registerContact(contact1_alt);
        Contact registeredContact2 = contactService.registerContact(contact2);
        Contact registeredContact3 = contactService.registerContact(contact3);
        Contact registeredContact4 = contactService.registerContact(contact4);
        
        System.out.println("Contact registered: " + registeredContact1.getName());
        System.out.println("Contact registered: " + registeredContact1_alt.getName());
        System.out.println("Contact registered: " + registeredContact2.getName());
        System.out.println("Contact registered: " + registeredContact3.getName());
        System.out.println("Contact registered: " + registeredContact4.getName());

        System.out.println("Contact ID to be searched: " + registeredContact1.getId() + " ( " + registeredContact1.getName() + " )");   
        Contact findContactById = contactService.findContactById(registeredContact1.getId());
        System.out.println("Contact searched by id: " + findContactById.getId() + " ( " + findContactById.getName() + " )");
        System.out.println("Contact name to be searched: " + contact1.getName());
        System.out.println("String searched: 'Jo'");
        List<Contact> searchByName = contactService.findContactsByName("Jo");
        System.out.println("Contacts found:");
        searchByName.forEach(contact -> System.out.println(contact.getName()));

        System.out.println("Deleting contact: " + contact1_alt.getName());
        contactService.deleteContact(contact1_alt.getId());
        System.out.println("String searched: 'Jo'"); 
        List<Contact> searchByName2 = contactService.findContactsByName("Jo");
        System.out.println("Contacts found:");
        searchByName2.forEach(contact -> System.out.println(contact.getName()));


        System.out.println("------------------ PRIVATE CHAT --------------");

        Chat privChat = chatService.createPrivateChat(contact1, contact2);

        chatService.sendMessage(privChat, contact1, contact1.getPresentation());
        chatService.sendMessage(privChat, contact2, contact2.getPresentation());

        System.out.println("Private Chat ID: " + privChat.getId());
        System.out.println("Private Chat Participants: ");

        privChat.getParticipants().forEach(
                participant -> {
                    System.out.println(
                            "Name: " + participant.getName() +
                                    "Presentation: " + participant.getPresentation());
                });
        System.out.println("Total chat participants: " + privChat.getParticipants().size());

        System.out.println("Messages sent");
        privChat.getMessages().forEach(
                message -> {
                    System.out.println(
                            "Sender: " + message.getSender().getName() +
                                    "\nSent at: " + message.getSentAt() +
                                    "\nContent: " + message.getContent());
                });

        System.out.println("------------------ END OF PRIVATE CHAT --------------");
        System.out.println("------------------ GROUP CHAT --------------");

        GroupRepository groupRepository = new GroupRepository();
        GroupServiceImpl groupService = new GroupServiceImpl(groupRepository, chatService);
        Group group = groupService.createGroup(contact4, "Software Engineers");

        groupService.addMember(group, contact1, contact4);
        groupService.addMember(group, contact2, contact4);
        groupService.addMember(group, contact3, contact4);

        System.out.println("Group's Name: " + group.getName());

        System.out.println("Group's Admin: " + group.getOwner().getName());

        System.out.println("Group Members: ");

        group.getAdmins().forEach(
                contact -> {
                    System.out.println(contact.getName());
                });
        group.getMembers().forEach(
                contact -> {
                    System.out.println(contact.getName());
                });

        chatService.sendMessage(group.getChat(), contact4, contact4.getPresentation());
        chatService.sendMessage(group.getChat(), contact3, contact3.getPresentation());
        chatService.sendMessage(group.getChat(), contact2, contact2.getPresentation());
        chatService.sendMessage(group.getChat(), contact1, contact1.getPresentation());

        System.out.println("Group Chat Messages: ");

        group.getChat().getMessages().forEach(
                message -> {
                    System.out.println(
                            "Sender: " + message.getSender().getName() +
                                    "\nSent at: " + message.getSentAt() +
                                    "\nContent: " + message.getContent());
                });

        System.out.println("------------------ END OF GROUP CHAT --------------");

    }

}
