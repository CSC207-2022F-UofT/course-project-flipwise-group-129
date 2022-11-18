package DataAccess;

import DataAccessInterface.GroupDataInterface;
//import org.*;
import java.io.FileWriter;

import DataAccessInterface.ItemDataInterface;
import org.json.simple.JSONObject;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.io.*;


public class ItemDataAccess implements ItemDataInterface {
    private final File jasonFile;
    private final Map<String, String> items = new HashMap<>();
    //gotta replace plannediteminfo with an abstract class or interface bc lots of datatypes need to fulfill
    public ItemDataAccess(String jasonPath) throws IOException, ParseException {
        // this parses the json file that already exists into a hashmap of id and info strings
//        this.jsonFile = new FileWriter(jasonPath);
        this.jasonFile = new File(jasonPath);
        FileReader reader = new FileReader(jasonFile);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        ((JSONArray) obj).forEach( itemObj -> parseItemObject( (JSONObject) itemObj ) );
    }
    private void parseItemObject(JSONObject itemObject){
        //Get item object within list
        JSONObject itemObjects = (JSONObject) itemObject.get("item");
        //Get itemId
        String itemId = (String) itemObjects.get("itemId");
        //Get itemInfo
        String itemInfo = (String) itemObjects.get("itemData");
        items.put(itemId, itemInfo);
    }
    private void save() throws IOException {
        //saves all the information once it's been updated
        FileWriter writer = new FileWriter(jasonFile);
        JSONArray groupList = new JSONArray();
        items.forEach((itemkey, itemData) -> {
            JSONObject itemDetails = new JSONObject();
            itemDetails.put("itemId", itemkey);
            itemDetails.put("itemData", itemData);
            JSONObject itemObject = new JSONObject();
            itemDetails.put("item", itemDetails);
            groupList.add(itemObject);
        });
        writer.write(groupList.toString());
        writer.flush();
    }

    @Override
    public void addorUpdateItem(String itemId, String itemInfo) throws IOException {
        // needs inputted the new or updated group to string alongside the group's id
        items.put(itemId, itemInfo);
        save();
    }

    @Override
    public String itemAsString(String itemId) {
        return items.get(itemId);
    }
}

