package Entities;

import java.util.ArrayList;

public class PlanningList extends ItemList{

    public PlanningList(){
        super.setItems(new ArrayList<>());
    }

    public boolean removeFromList(Item item){
        if (super.getItems().contains(item)){
            super.getItems().remove(item);
            return true;
        }else{
            return false;
        }
    }
}
