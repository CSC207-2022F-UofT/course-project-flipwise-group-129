package InterfaceAdapters.Presenters;
import UseCaseLayer.DataStructures.UpdatedDebts;
import UseCaseLayer.OutputBoundary.SettlementBoundaryOut;
public class SettlementPresenter implements SettlementBoundaryOut{
    /**
     * This function is called in the SettlementPayment use case to pass the successful results as updated lists
     * so that the view can update accordingly.
     * @param debtList a list that contains the updated debts in the group to pass to the view
     * @return This returns an instance of the UpdatedLists data structure that packages information on a group's current
     * debts so that it is usable for the view.
     */
    @Override
    public UpdatedDebts displayDebts(UpdatedDebts debtList){
        return debtList;
    }
    /**
     * This function is called in the SettlementPayment use case to pass the failed results as a message
     * so that the view can update accordingly.
     * @param errorString a string that describes the failure that occurred in the SettlementPayment use case.
     * @return This returns an instance of the UpdatedLists data structure that contains a failure description string.
     */
    @Override
    public UpdatedDebts failErrorMessage(UpdatedDebts errorString){
        return errorString;
    }
}