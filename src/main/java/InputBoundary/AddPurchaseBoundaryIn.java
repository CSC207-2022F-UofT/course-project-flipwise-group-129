package InputBoundary;

import DataStructures.PurchaseInfo;

public interface AddPurchaseBoundaryIn {
    // Defining the functions that will be implemented by the AddPurchase use case through the boundary to maintain clean architecture
    public void executeUseCase(PurchaseInfo purchaseInfo);
}
