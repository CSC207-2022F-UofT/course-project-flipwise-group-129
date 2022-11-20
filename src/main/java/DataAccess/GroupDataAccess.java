package DataAccess;

//import org.*;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;

package DataAccess;
import DataAccessInterface.*;

public class GroupDataAccess extends DataAccess implements GroupDataInterface {
    public final File groupFile = new File("users.json");;
    private final Map<String, String> groupMap = new HashMap<>();
    public GroupDataAccess() throws IOException, ParseException {
        super.readFile(groupFile, groupMap);
    }
    @Override
    public void addorUpdateGroup(String groupId, String groupInfo) throws IOException {
        super.addorUpdateEntity(groupFile, groupMap, groupId, groupInfo);
    }

    @Override
    public boolean groupIdExists(String groupId) {
        return groupMap.containsKey(groupId);
    }

    @Override
    public String groupAsString(String groupId) {
        return groupMap.get(groupId);
    }
}
