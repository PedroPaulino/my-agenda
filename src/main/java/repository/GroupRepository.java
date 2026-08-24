package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Group;

public class GroupRepository {
    private List<Group> mockGroupDb;

    public Group findGroupById(UUID id){

        return mockGroupDb.stream()
            .filter( group -> group.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Group> findGroupsByName(String groupName){
        List<Group> result = new ArrayList<>();

        for (Group group : mockGroupDb) {
            
            if (group.getName().contains(groupName)){
                result.add(group);
            }
        }
        
        return result;

    }

    public Group saveGroup(Group group){

        try {
            mockGroupDb.add(group);
            return group;
        } catch (Exception e) {
            return null;
        }

    }

    public Boolean deleteGroup(Group group){
        return mockGroupDb.remove(group);
    }

}
