package Entities;

import java.util.List;

public class Item {
    private String itemName;
    private User buyer;
    private Float price;

    private String itemId;
    private List<User> usersInvolved;

    public Item(String itemName, User buyer, Float price, List<User> usersInvolved){
        this.itemName = itemName;
        this.buyer = buyer;
        this.price = price;
        this.usersInvolved = usersInvolved;
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

    public String getItemId(){ return this.itemId; }

    public void setPrice(Float price){
        this.price = price;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public void setBuyer(User buyer){
        this.buyer = buyer;
    }

    public void setUsersInvolved(List<User> users){
        this.usersInvolved = users;
    }
}
