package DataAccess;

import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;
import DataAccessInterface.GroupDataInterface;

public class GroupDataAccess extends DataAccess implements GroupDataInterface {
    /**
     * Implements GroupDataInterface
     * This represents an GroupDataAccess which facilitates data reading and writing from groups.json to update
     */
    public File groupFile = new File("src/main/groups.json");
    private final Map<String, String> groupMap = new HashMap<>();

    /**
     * Creates a group data access instance by first reading the groups.json file and storing all information locally
     */
    public GroupDataAccess() throws IOException, ParseException {
        super.readFile(groupFile, groupMap);
    }

    /**
     * Creates a test group data access instance by first reading and storing the testgroups.json file locally
     * @param testString a string confirming database to access is test
     */
    public GroupDataAccess(String testString) throws IOException, ParseException {
        this.groupFile = new File("src/main/testgroups.json");
        super.readFile(groupFile, groupMap);
    }

    /**
     * This function will be called to add or update any group entity to the map and saving the updated information
     * back into the groups.json file
     * @param groupId the groupId of the current modified or added group
     * @param groupInfo the summarized group information of the current modified or added group
     */
    @Override
    public void addorUpdateGroup(String groupId, String groupInfo) throws IOException {
        super.addorUpdateEntity(groupFile, groupMap, groupId, groupInfo);
    }

    /**
     * This function will be called to check if a group entity already has been entered in groups.json file
     * @param groupId the groupId of the current modified or added group
     * @return true if the groupId was found from the groups.json file and false otherwise
     */
    @Override
    public boolean groupIdExists(String groupId) {
        return groupMap.containsKey(groupId);
    }

    /**
     * This function will be called to retrieve the stored information from groups.json of a group based on its groupId
     * @param groupId the groupId of the desired group
     * @return group details corresponding to groupId in string form
     */
    @Override
    public String groupAsString(String groupId) {
        return groupMap.get(groupId);
    }

    /**
     * This function will be called in the tests to check if the database updated as expected
     * @return the whole groupMap read from groups.json
     */
    public Map<String, String> getGroupMap(){
        return groupMap;
    }
}
