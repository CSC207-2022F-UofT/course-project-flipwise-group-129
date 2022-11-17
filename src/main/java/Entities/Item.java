package Entities;

public class Item {
    private String itemName;
    private User buyer;
    private Float price;

    public Item(String itemName, User buyer, Float price){
        this.itemName = itemName;
        this.buyer = buyer;
        this.price = price;
    }

    public String getItemName(){
        return itemName;
    }

    public User getBuyer(){
        return buyer;
    }

    public Float getPrice(){
        return price;
    }

    public void setPrice(Float price){
        this.price = price;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public void setBuyer(User buyer){
        this.buyer = buyer;
    }
}
