package Entities;

import java.util.ArrayList;

public class PurchaseList extends ItemList{

    public PurchaseList(){
        super.setItems(new ArrayList<>());
    }

    @Override
    public String toString() {
        return this.toJSON().toJSONString();
    }
}
