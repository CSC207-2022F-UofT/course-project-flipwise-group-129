package DataStructures;

import Entities.PlanningList;
import Entities.PurchaseList;

public class UpdatedLists {
    private final PlanningList newPlanningList;
    private final PurchaseList newPurchasedList;

    public UpdatedLists(PlanningList updatedPlanning, PurchaseList updatedPurchase) {
        this.newPlanningList = updatedPlanning;
        this.newPurchasedList = updatedPurchase;
    }

    public PlanningList getNewPlanningList(){
        return this.newPlanningList;
    }

    public PurchaseList getNewPurchasedList(){
        return this.newPurchasedList;
    }
}
