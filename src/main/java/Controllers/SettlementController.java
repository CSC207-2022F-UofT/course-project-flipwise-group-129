package Controllers;
import DataStructures.UpdatedDebts;
import InputBoundary.SettlementBoundaryIn;
import DataStructures.PaymentDetails;
/**
 * This function will be called from the main/view to execute the use case AddtoPlanningList, which occurs
 * when a user wants to add a new item to the planning list. This function packages required information on the item
 * and current group received from the view into a PlannedItemInfo data structure. This information is passed to the
 * input boundary to perform the use case.
 */
public class SettlementController {
    public final SettlementBoundaryIn inputBoundary;
    /**
     * Creates a new SettlementController to execute the SettlementPayment use case
     * @param inputBoundary an instance of the input boundary passed in from the main/view to execute the use case
     */
    public SettlementController(SettlementBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    /**
     * This function will be called from the main/view to execute the use case SettlementPayment, which occurs
     * when a user wants to settle a debt with another user in group. This function packages required information on the user
     * and user to pay received from the view into a PaymentDetails data structure. This information is passed to the
     * input boundary to perform the use case.
     * @param owedUser name of the user owed money
     * @param owingUser name of the user settling up
     * @param groupId the group they belong to
     * @return This returns an instance of UpdatedLists data structure that contains updated list of debts in the group,
     * or if there is an error in the use case, a string that describes the error.
     */
    public UpdatedDebts settleDebt(String owedUser, String owingUser, String groupId) {
        PaymentDetails itemInfo = new PaymentDetails(owedUser, owingUser, groupId);
        return inputBoundary.executeDebtSettlement(itemInfo);
    }
}