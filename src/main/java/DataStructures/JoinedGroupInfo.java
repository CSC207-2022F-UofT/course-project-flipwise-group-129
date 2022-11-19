package DataStructures;

import java.util.List;

public class JoinedGroupInfo {

    private final List<String> usersInGroup;
    private final List<String> groupIds;
    private final List<String> groupNames;

    public JoinedGroupInfo(List<String> usersInGroup, List<String> groupIds, List<String> groupNames){
        this.groupIds = groupIds;
        this.groupNames = groupNames;
        this.usersInGroup = usersInGroup;
    }

    public List<String> getUsersInGroup(){ return this.usersInGroup; }
    public List<String> getGroupIds() { return this.groupIds; }
    public List<String> getGroupNames() { return this.groupNames; }

}
