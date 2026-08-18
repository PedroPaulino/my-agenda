package domain;

import java.util.UUID;

public class Contact {
    
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String role;
    private String address;
    private String presentation;
    private Chat chat;
    private Group group;

    public Contact(String name, String email, String phone, String department, String role, String address,
            String presentation, Chat chat, Group group) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.role = role;
        this.address = address;
        this.presentation = presentation;
        this.chat = chat;
        this.group = group;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPresentation() {
        return presentation;
    }
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    public Chat getChat() {
        return chat;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    

}
