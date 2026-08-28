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

    public void saveGroup(Group group){

        mockGroupDb.add(group);


    }

    public void deleteGroup(Group group){
        mockGroupDb.remove(group);
    }

}
