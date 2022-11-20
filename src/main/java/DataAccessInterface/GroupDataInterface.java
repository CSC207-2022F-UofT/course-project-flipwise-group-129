package DataAccessInterface;
import java.io.IOException;

public interface GroupDataInterface {

    void addorUpdateGroup(String id, String groupDetails) throws IOException;

    boolean groupIdExists(String id);

    String groupAsString(String username);
}