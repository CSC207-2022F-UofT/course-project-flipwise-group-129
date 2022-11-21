package DataAccessInterface;
import java.io.IOException;

public interface GroupDataInterface {

    /**
     * This function will be called to add or update any group entity to the map and saving the updated information
     * back into the groups.json file
     * @param groupId the groupId of the current modified or added group
     * @param groupInfo the summarized group information of the current modified or added group
     */
    void addorUpdateGroup(String groupId, String groupInfo) throws IOException;

    /**
     * This function will be called to check if a group entity already has been entered in groups.json file
     * @param groupId the groupId of the current modified or added group
     * @return true if the groupId was found from the groups.json file and false otherwise
     */
    boolean groupIdExists(String groupId);

    /**
     * This function will be called to retrieve the stored information from groups.json of a group based on its groupId
     * @param groupId the groupId of the desired group
     * @return group details corresponding to groupId in string form
     */
    String groupAsString(String groupId);
}