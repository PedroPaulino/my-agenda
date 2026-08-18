package service;

import java.util.List;
import java.util.UUID;

import domain.Contact;
import domain.Group;

public interface GroupService {

    public Group findGroupById(UUID id);
    public List<Group> findGroupsByName(String name);
    public Group createGroup(Group group, Contact owner);
    public void addMember(Group group, Contact member, Contact requester);
    public void removeMember(Group group, Contact member, Contact requester);
    public void grantAdmin(Group group, Contact member, Contact requester);
    public void revokeAdmin(Group group, Contact member, Contact requester);
    public void deleteGroup(Group group, Contact requester);
    public boolean isOwner(Group group, Contact contact);
    public boolean isAdmin(Group group, Contact contact);

}
