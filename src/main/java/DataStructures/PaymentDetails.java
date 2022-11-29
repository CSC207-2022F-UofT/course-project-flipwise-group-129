package DataStructures;

public class PaymentDetails {
    final private String owedUser;
    final private String owingUser;
    final private String groupId;

    /**
     * Creates a new package of information needed to execute the AddtoPlanning use case which consists of
     * information on the new item to add and its group
     * @param owedUser the user owed money
     * @param owingUser the user owing money
     * @param groupId the groupId of the group storing this new item
     */
    public PaymentDetails(String owedUser, String owingUser, String groupId){
        this.owedUser = owedUser;
        this.owingUser = owingUser;
        this.groupId = groupId;
    }

    /**
     * This function retrieves the username of the owed user in the debt
     * @return This returns the username of the owed user in the debt
     */
    public String getOwed(){    return owedUser;    }

    /**
     * This function retrieves the username of the owing user in the debt
     * @return This returns the username of the owing user in the debt
     */
    public String getOwing(){    return owingUser;    }

    /**
     * This function retrieves the groupId of the group that contains this debt
     * @return This returns the groupId of the group that contains this debt
     */
    public String getGroupId(){ return groupId; }
}