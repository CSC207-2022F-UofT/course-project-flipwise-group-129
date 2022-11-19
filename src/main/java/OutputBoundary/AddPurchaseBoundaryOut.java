package OutputBoundary;

import DataStructures.UpdatedLists;

public interface AddPurchaseBoundaryOut {
    // Defining the functions that will be implemented by the AddPurchase presenter in the output boundary so that the use case may call the presenter and update the view without violating clean architecture
    public void updateView(UpdatedLists updatedLists);
    // This function uses the updated list data structure as a parameter as the new planning and purchase list are what will be updated on the view after add purchase is called
}
