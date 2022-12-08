package UseCaseLayer.DataStructures;
public class ProposedGroupInfo {
     /*
     Represents all the information passed in by the user to create a new group
      */
    private final String username; // the name of the user making a new group
    private final String groupName; // the name of the group the user wants to make


    /**
     * Create an object representing all the information to create a group
     * @param username the name of the user requesting this creation
     * @param groupName the name of the new group to be created
     */
    public ProposedGroupInfo(String username, String groupName){
        this.groupName = groupName;
        this.username = username;
    }

    /**
     * get the name of the user requesting creation
     * @return the user requesting creation
     */
    public String getUsername(){
        //return the name of the user who is performing this action
        return this.username;
    }

    /**
     * get the name of the group to be created
     * @return the name of the group to be created
     */

    public String getGroupName() {
        //return the name of the group that the user wants to create
        return this.groupName;
    }


}
