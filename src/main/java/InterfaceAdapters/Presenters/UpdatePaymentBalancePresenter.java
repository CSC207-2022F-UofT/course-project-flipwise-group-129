package InterfaceAdapters.Presenters;

import UseCaseLayer.DataStructures.UpdatedDebts;
import UseCaseLayer.OutputBoundary.UpdatePaymentBalanceBoundaryOut;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UpdatePaymentBalancePresenter implements UpdatePaymentBalanceBoundaryOut{

    /**
     * Prepares and returns the information changed by the use case to the view.
     * @param updatedDebts the data structure with the updated list of debts for the group.
     * @return an UpdatedDebts object containing the updated information of the debts in the group between users.
     */
    @Override
    public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) throws IOException, ParseException {
        return updatedDebts;
    }

    /**
     * Returns the information from an error in the form of an error message to the view.
     * @param updatedDebts contains the error message raised.
     * @return the data structure containing the error information.
     */
    @Override
    public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
        return updatedDebts;
    }
}
