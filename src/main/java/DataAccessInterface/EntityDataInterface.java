package DataAccessInterface;

import java.io.IOException;

public interface EntityDataInterface {

    void addorUpdateEntity(String id, String entityDetails) throws IOException;

    boolean entityIdExists(String id);

    String entityAsString(String id);
}
