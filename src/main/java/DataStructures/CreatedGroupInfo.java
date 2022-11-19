package DataStructures;

import java.util.List;
public class CreatedGroupInfo {

    private final String groupId;

    private final String user;

    private final List<String> groupIds;

    private final List<String> groupNames;

    public CreatedGroupInfo(String groupId, String user, List<String> groupIds, List<String> groupNames){
        this.groupId = groupId;
        this.user = user;
        this.groupIds = groupIds;
        this.groupNames = groupNames;
    }

    public String getId(){
        return this.groupId;
    }

    public String getUsers(){
        return this.user;
    }

    public List<String> getAllGroupIds(){
        return this.groupIds;
    }

    public List<String> getAllGroupNames(){
        return this.groupNames;
    }



}
