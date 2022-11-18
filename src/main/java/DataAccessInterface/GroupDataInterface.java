package DataAccessInterface;

import java.io.IOException;

public interface GroupDataInterface {
    void addorUpdateGroup(String groupId, String groupInfo) throws IOException;
    String groupAsString(String groupId);
}
