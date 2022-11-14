package Entities;

public class Item {
    private String itemName;
    private String buyer;
    private Float price;

    public Item(String itemName, String buyer, Float price){
        this.itemName = itemName;
        this.buyer = buyer;
        this.price = price;
    }

    public String getItemName(){
        return itemName;
    }

    public String getBuyer(){
        return buyer;
    }

    public Float getPrice(){
        return price;
    }
}
