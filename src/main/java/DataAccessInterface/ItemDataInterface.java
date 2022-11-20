package DataAccessInterface;

import java.io.IOException;

public interface ItemDataInterface {
    void addorUpdateItem(String itemId, String itemInfo) throws IOException;
    String itemAsString(String itemId);
}
