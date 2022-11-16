package Entities;
import java.util.ArrayList;
import java.util.List;

public class PurchaseBalance {

    private List<Debt> allDebts;

    public PurchaseBalance(){
        this.allDebts = new ArrayList<>();
    }
    public PurchaseBalance(List<Debt> debts){
        this.allDebts = debts;
    }


    public List<Debt> getAllDebts() {
        return allDebts;
    }

    public void addDebtPair(Debt debt){
        this.allDebts.add(debt);
    }

    public boolean removeDebtPair(Debt debt){
        if (this.allDebts.contains(debt)){
            this.allDebts.remove(debt);
            return true;
        }
        return false;
    }
}
