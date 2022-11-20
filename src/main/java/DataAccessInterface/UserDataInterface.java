package DataAccessInterface;

import java.io.IOException;

public interface UserDataInterface {

    void addorUpdateUser(String id, String entityDetails) throws IOException;

    boolean userIdExists(String id);

    String userAsString(String username);
}
