package UseCaseLayer.OutputBoundary;
import UseCaseLayer.DataStructures.UpdatedDebts;
public interface SettlementBoundaryOut {
    /**
     * This function is called in the SettlementPayment use case to return the updated view with the new
     * debts that have been updated
     * @param debtList a list that contains all the new updated lists in the group
     * @return This returns an instance of the UpdatedLists data structure that packages information on a group's current     *  debts to that it is usable for the view.
     */
    UpdatedDebts displayDebts(UpdatedDebts debtList);
    /**
     * This function is called in the SettlementPayment use case to pass the failed results as a message
     * so that the view can update accordingly.
     * @param errorString a string that describes the failure that occurred in the SettlementPayment use case.
     * @return This returns an instance of the UpdatedLists data structure that contains a failure description string.
     */
    UpdatedDebts failErrorMessage(UpdatedDebts errorString);
}
