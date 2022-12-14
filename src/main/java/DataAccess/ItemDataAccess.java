package DataAccess;

import DataAccessInterface.ItemDataInterface;


import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;

public class ItemDataAccess extends DataAccess implements ItemDataInterface {
    /**
     * Implements ItemDataInterface-
     * This is an ItemDataAccess which facilitates data reading and writing from items.json to update
     */
    public File itemFile = new File("././src/main/items.json");
    private final Map<String, String> itemMap = new HashMap<>();

    /**
     * Creates an item data access instance by first reading the items.json file and storing all information locally
     */
    public ItemDataAccess() throws IOException, ParseException {
        super.readFile(itemFile, itemMap);
    }

    /**
     * Creates a test item data access instance by first reading and storing the testitems.json file locally
     * @param testString a string confirming database to access is test
     */
    public ItemDataAccess(String testString) throws IOException, ParseException {
        if(!Objects.equals(testString, "")){
            this.itemFile = new File("././src/test/resources/testitemsCopy.json");
            super.readFile(new File("././src/test/resources/testitemsCopy.json"), itemMap);
        }
    }

    /**
     * This function will be called to add or update any item entity to the map and saving the updated information
     * back into the items.json file
     * @param itemId the itemId of the current modified or added item
     * @param itemInfo the summarized item information of the current modified or added item
     */
    @Override
    public void addorUpdateItem(String itemId, String itemInfo) throws IOException, ParseException {
        super.readFile(itemFile, itemMap);
        super.addorUpdateEntity(itemFile, itemMap, itemId, itemInfo);
    }

    /**
     * This function will be called to check if an item entity already has been entered in items.json file
     * @param itemId the itemId of the current modified or added item
     * @return true if the itemId was found from the items.json file and false otherwise
     */
    @Override
    public boolean itemIdExists(String itemId) throws IOException, ParseException {
        super.readFile(itemFile, itemMap);
        return itemMap.containsKey(itemId);
    }

    /**
     * This function will be called to retrieve the stored information from items.json of an item based on its itemId
     * @param itemId the itemId of the desired item
     * @return item details corresponding to itemId in string form
     */
    @Override
    public String itemAsString(String itemId) throws IOException, ParseException {
        super.readFile(itemFile, itemMap);
        return itemMap.get(itemId);
    }

    /**
     * This function will be called in the tests to check if the database updated as expected
     * @return the whole itemMap read from items.json
     */
    public Map<String, String> getItemMap(){
        return itemMap;
    }
}
