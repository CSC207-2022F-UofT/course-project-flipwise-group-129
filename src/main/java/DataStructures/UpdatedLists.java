package DataStructures;

import java.util.List;

public class UpdatedLists {
    // Define the class attributes to store a 2D list for both the new planning and purchase list to store the item ids and the names
    private final List<List<String>> newPlanningList;
    private final List<List<String>> newPurchasedList;

    public UpdatedLists(List<List<String>> updatedPlanning, List<List<String>> updatedPurchase) {
        // Instantiate the class attributes from the information returned from the use case
        this.newPlanningList = updatedPlanning;
        this.newPurchasedList = updatedPurchase;
    }

    // Implementing getter functions for the new planning and purchased lists so that the presenter can use them to update the view
    public List<List<String>> getNewPlanningList(){
        return this.newPlanningList;
    }

    public List<List<String>> getNewPurchasedList(){
        return this.newPurchasedList;
    }
}
