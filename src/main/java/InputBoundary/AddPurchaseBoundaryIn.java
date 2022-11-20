package InputBoundary;

import DataStructures.PurchaseInfo;

public interface AddPurchaseBoundaryIn {
    // Defining the functions that will be implemented by the AddPurchase use case through the boundary to
    // maintain clean architecture

    /**
     * Removes the item being purchased from the planning list and assigns the buyer, price, and users involved in
     * the purchase
     * to the item entity object then adds it to the purchased list of the group, saves the new group information to
     * the database,
     * then returns the new lists to be displayed to the view
     * @param purchaseInfo the data structure containing all the information required to make a purchase and update
     *                     the necessary data
     */
    public void executeUseCase(PurchaseInfo purchaseInfo);
}
