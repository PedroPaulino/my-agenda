package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import domain.Chat;
import domain.Contact;
import domain.Group;
import exception.MyAgendaException;
import repository.GroupRepository;

public class GroupServiceImpl implements GroupService{
    private GroupRepository repository;
    private ChatServiceImpl chatService;

    public GroupServiceImpl(GroupRepository repository, ChatServiceImpl chatService){
        this.repository = repository;
        this.chatService = chatService;
    }

    @Override
    public void addMember(Group group, Contact member, Contact requester) {
        
        if ( !isAdmin(group, requester) ) {
            throw new MyAgendaException("Requester: " + requester.getName() + " is not Group's " + group.getName() + " admin. Please request a group's admin to add " + member.getName() + " as a member.");
        }
        
        group.getMembers().add(member);

        group.getChat().getParticipants().add(member);

    }

    @Override
    public Group createGroup(Contact owner, String groupName) {
        
        if (groupName == null) {
            throw new MyAgendaException("Group name cannot be null. Please provide a valid group name");
        }

        if ( groupName.isBlank() || groupName.isEmpty()){
            throw new MyAgendaException("Group name cannot be blank or empty. Please provide a valid group name");
        }

        Set<Contact> participants = new HashSet<Contact>();
        participants.add(owner);

        Set<Contact> admins = new HashSet<Contact>();
        admins.add(owner);

        Chat groupChat = chatService.createGroupChat(owner);

        Group group = new Group(groupName, owner, groupChat, participants, admins);

        repository.saveGroup(group);

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

        group.getChat().getParticipants().remove(member);
        
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
