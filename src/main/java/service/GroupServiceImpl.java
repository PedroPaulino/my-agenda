package service;

import java.util.List;
import java.util.UUID;

import domain.Contact;
import domain.Group;
import repository.GroupRepository;

public class GroupServiceImpl implements GroupService{
    private GroupRepository repository;

    GroupServiceImpl(GroupRepository repository){
        this.repository = repository;
    }

    @Override
    public void addMember(Group group, Contact member, Contact requester) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Group createGroup(Group group, Contact owner) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteGroup(Group group, Contact requester) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Group findGroupById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Group> findGroupsByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void grantAdmin(Group group, Contact member, Contact requester) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAdmin(Group group, Contact contact) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isOwner(Group group, Contact contact) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeMember(Group group, Contact member, Contact requester) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void revokeAdmin(Group group, Contact member, Contact requester) {
        // TODO Auto-generated method stub
        
    }

    
    
}
