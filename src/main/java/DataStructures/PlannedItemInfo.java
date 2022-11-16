package DataStructures;

public class PlannedItemInfo {
    final private String name;
    final private Float price;
    final private String groupId;

    public PlannedItemInfo(String name, Float price, String groupId){
        this.name = name;
        this.price = price;
        this.groupId = groupId;
    }

    public String getName(){    return name;    }
    public Float getPrice(){    return price;   }
    public String getGroupId(){ return groupId; }
}
