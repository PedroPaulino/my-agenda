package service;

import java.util.List;
import java.util.UUID;

import domain.Contact;
import domain.Group;
import exception.MyAgendaException;
import repository.GroupRepository;

public class GroupServiceImpl implements GroupService{
    private GroupRepository repository;
    private ContactServiceImpl contactService;
    private ChatServiceImpl chatService;

    public GroupServiceImpl(GroupRepository repository, ContactServiceImpl contactService){
        this.repository = repository;
        this.contactService = contactService;
    }

    @Override
    public void addMember(Group group, Contact member, Contact requester) {
        
        if ( !isAdmin(group, requester) ) {
            throw new MyAgendaException("Requester: " + requester.getName() + " is not Group's " + group.getName() + " admin. Please request a group's admin to add " + member.getName() + " as a member.");
        }

        contactService.validateContact(member);
        
        group.getMembers().add(member);

    }

    @Override
    public Group createGroup(Group group, Contact owner) {
        
        /* TODO: Update SDD - sequence diagram */

        contactService.validateContact(owner);

        repository.saveGroup(group);

        chatService.createGroupChat(group);

        return group;
    }

    @Override
    public void deleteGroup(Group group, Contact requester) {
        
        if ( !isOwner(group, requester) ) {
            throw new MyAgendaException("Requester: " + requester.getName() + " is not Group's " + group.getName() + " owner. Please request the group's owner to delete the group");
        }
        
        chatService.deleteChat(group.getChat());
        
        repository.deleteGroup(group);
        
    }

    @Override
    public Group findGroupById(UUID id) {
        
        Group resultGroup = repository.findGroupById(id);

        if (resultGroup == null) {
            
            throw new MyAgendaException("Group ID: " + id + " Not found. Please provide a valid ID");

        }

        return resultGroup;
    }

    @Override
    public List<Group> findGroupsByName(String name) {
        
        if ( name.isBlank() || name.isEmpty() ) {
            throw new MyAgendaException("Group name cannot be Empty or Blank. Please provide a valid name");
        }

        return repository.findGroupsByName(name);
    }

    @Override
    public void grantAdmin(Group group, Contact member, Contact requester) {
        
        contactService.validateContact(member);
        
        if ( !isOwner(group, requester)) {
            throw new MyAgendaException("Only the group's owner can grant admin.");
        }

        group.getAdmins().add(member);
        
    }

    @Override
    public boolean isAdmin(Group group, Contact contact) {
        
        if ( group.getAdmins().contains(contact) )
            return true;

        return false;
    }

    @Override
    public boolean isOwner(Group group, Contact contact) {
        
        if ( group.getOwner().getId().equals(contact.getId()) ) {
            return true;
        }

        return false;
    }

    @Override
    public void removeMember(Group group, Contact member, Contact requester) {

        if ( !isAdmin(group, requester) ) {
            throw new MyAgendaException("Requester: " + requester.getName() + " is not Group's " + group.getName() + " admin. Please request a group's admin to remove " + member.getName() + " as a member.");
        }
        
        group.getMembers().remove(member);
        
    }

    @Override
    public void revokeAdmin(Group group, Contact member, Contact requester) {
        
        if ( !isOwner(group, requester)) {
            throw new MyAgendaException("Only the group's owner can grant admin.");
        }

        group.getAdmins().remove(member);
        
    }

    public void validateGroup(Group group, Contact owner){

        if ( group.getName() == null || group.getName().isEmpty() || group.getName().isBlank()) {
            throw new MyAgendaException("Group name cannot be null, empty or have only white spaces.");
        }

        if (group.getAdmins().isEmpty()){
            throw new MyAgendaException("Group must have at least one admin");
        }

        if (!group.getOwner().equals(owner)){
            throw new MyAgendaException("Group owner id: " + group.getOwner().getId() + " Does not match with owner id provided: " + owner.getId());
        } 

        if (!group.getMembers().contains(owner) && group.getMembers().size() < 1) {
            throw new MyAgendaException("Group members must contain at least the group's owner");
        }


    }
    
}
