package DataStructures;

import java.util.List;

public class UpdatedLists {
    private final List<String> newPlanningList;
    private final List<String> newPurchasedList;

    public UpdatedLists(List<String> updatedPlanning, List<String> updatedPurchase) {
        this.newPlanningList = updatedPlanning;
        this.newPurchasedList = updatedPurchase;
    }

    public List<String> getNewPlanningList(){
        return this.newPlanningList;
    }

    public List<String> getNewPurchasedList(){
        return this.newPurchasedList;
    }
}
