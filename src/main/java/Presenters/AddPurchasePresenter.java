package Presenters;

import DataStructures.UpdatedLists;
import OutputBoundary.AddPurchaseBoundaryOut;

import java.util.HashMap;
import java.util.List;

public class AddPurchasePresenter implements AddPurchaseBoundaryOut {

    /**
     * Prepares and returns the information changed by the use case to the view
     * @param updatedLists the data structure with the new planning and purchased lists
     * @return a hashmap mapping the name of each list to the actual list
     */
    @Override
    public HashMap<String, List<List<String>>> prepareViewInformation(UpdatedLists updatedLists) {
        // Update the view
        HashMap<String, List<List<String>>> newLists = new HashMap<String, List<List<String>>>();
        newLists.put("planning list", updatedLists.getNewPlanningList());
        newLists.put("purchased list", updatedLists.getNewPurchasedList());

        return newLists;
    }

//    speak to the view
}
