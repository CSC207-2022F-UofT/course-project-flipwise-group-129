package DataStructures;

public class JoinGroupRequest {

    private String groupId;

    private String userId;

    public JoinGroupRequest(String groupId, String userId){
        this.groupId = groupId;
        this.userId = userId;
    }

    public String getGroupId() { return this.groupId; }
    public String getUserId() { return this.userId; }
}
