package DataStructures;

import java.util.List;

public class UpdatedLists {
    private final List<List<String>> newPlanningList;
    private final List<List<String>> newPurchasedList;

    public UpdatedLists(List<List<String>> updatedPlanning, List<List<String>> updatedPurchase) {
        this.newPlanningList = updatedPlanning;
        this.newPurchasedList = updatedPurchase;
    }

    public List<List<String>> getNewPlanningList(){
        return this.newPlanningList;
    }

    public List<List<String>> getNewPurchasedList(){
        return this.newPurchasedList;
    }
}
