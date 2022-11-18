package DataAccessInterface;
import DataStructures.*;

import java.io.IOException;

public interface GroupDataInterface {
    void addGroup(String groupId, String groupInfo) throws IOException;
    String groupAsString(String groupId);
}
