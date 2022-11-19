package DataStructures;
public class ProposedGroupInfo {
     /*
     Represents all the information passed in by the user to create a new group
      */
    private final String username; // the name of the user making a new group
    private final String groupName; // the name of the group the user wants to make


    public ProposedGroupInfo(String username, String groupName){
        this.groupName = groupName;
        this.username = username;
    }

    public String getUsername(){
        //return the name of the user who is performing this action
        return this.username;
    }

    public String getGroupName() {
        //return the name of the group that the user wants to create
        return this.groupName;
    }


}
