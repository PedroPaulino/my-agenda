package domain;

import java.util.Set;
import java.util.UUID;

public class Group {
    private UUID id;
    private String name;
    private Contact owner;
    private Chat chat;
    private Set<Contact> members;
    private Set<Contact> admins;

    public Group(String name, Contact owner, Chat chat, Set<Contact> members, Set<Contact> admins) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.owner = owner;
        this.chat = chat;
        this.members = members;
        this.admins = admins;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Contact getOwner() {
        return owner;
    }
    public void setOwner(Contact owner) {
        this.owner = owner;
    }
    public Chat getChat() {
        return chat;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public Set<Contact> getMembers() {
        return members;
    }
    public void setMembers(Set<Contact> members) {
        this.members = members;
    }
    public Set<Contact> getAdmins() {
        return admins;
    }
    public void setAdmins(Set<Contact> admins) {
        this.admins = admins;
    }

    

}
