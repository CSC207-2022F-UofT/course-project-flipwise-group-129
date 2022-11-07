package Entities;
import java.util.ArrayList;
import java.util.List;

public class PurchaseBalance {

    private ArrayList<Debt> allDebts;

    public PurchaseBalance(){
        this.allDebts = new ArrayList<>();
    }

    public ArrayList<Debt> getAllDebts() {
        return allDebts;
    }
}
