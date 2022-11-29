package DataAccess;

import DataAccessInterface.ItemDataInterface;

import java.io.File;

import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;

public class ItemDataAccess extends DataAccess implements ItemDataInterface {
    public final File itemFile = new File("src/main/items.json");;
    private final Map<String, String> itemMap = new HashMap<>();
    public ItemDataAccess() throws IOException, ParseException {
        super.readFile(itemFile, itemMap);
    }
    @Override
    public void addorUpdateItem(String itemId, String itemInfo) throws IOException {
        super.addorUpdateEntity(itemFile, itemMap, itemId, itemInfo);
    }

    @Override
    public boolean itemIdExists(String itemId) {
        return itemMap.containsKey(itemId);
    }

    @Override
    public String itemAsString(String itemId) {
        return itemMap.get(itemId);
    }
}
