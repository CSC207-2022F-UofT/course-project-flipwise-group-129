package UseCaseLayer.DataAccessInterface;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public interface ItemDataInterface {
    /*
     * This represents a ItemDataInterface defines methods needed to
     * facilitate data reading and writing from items.json
     */

    /**
     * This function will be called to add or update any item entity to the map and saving the updated information
     * back into the items.json file
     * @param itemId the itemId of the current modified or added item
     * @param itemInfo the summarized item information of the current modified or added item
     */
    void addorUpdateItem(String itemId, String itemInfo) throws IOException, ParseException;

    /**
     * This function will be called to check if an item entity already has been entered in items.json file
     * @param itemId the itemId of the current modified or added item
     * @return true if the itemId was found from the items.json file and false otherwise
     */
    String itemAsString(String itemId) throws IOException, ParseException;

    /**
     * This function will be called to retrieve the stored information from items.json of an item based on its itemId
     * @param itemId the itemId of the desired item
     * @return item details corresponding to itemId in string form
     */
    boolean itemIdExists(String itemId) throws IOException, ParseException;

    /**
     * This function will be called in the tests to check if the database updated as expected
     * @return the whole itemMap read from items.json
     */
    Map<String, String> getItemMap();
}
