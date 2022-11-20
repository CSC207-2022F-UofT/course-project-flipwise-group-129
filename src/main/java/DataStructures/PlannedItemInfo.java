package DataStructures;

public class PlannedItemInfo {
    final private String name;
    final private Float price;
    final private String groupId;

    /**
     * Creates a new package of information needed to execute the AddtoPlanning use case which consists of
     * information on the new item to add and its group
     * @param name the new item's name
     * @param price the new item's price
     * @param groupId the groupId of the group storing this new item
     */
    public PlannedItemInfo(String name, Float price, String groupId){
        this.name = name;
        this.price = price;
        this.groupId = groupId;
    }

    /**
     * This function retrieves the name of the item in the package
     * @return This returns the name of the item in the package
     */
    public String getName(){    return name;    }

    /**
     * This function retrieves the price of the item in the package
     * @return This returns the price of the item in the package
     */
    public Float getPrice(){    return price;   }

    /**
     * This function retrieves the groupId of the group of the item in the package
     * @return This returns the groupId of the group of the item in the package
     */
    public String getGroupId(){ return groupId; }
}
