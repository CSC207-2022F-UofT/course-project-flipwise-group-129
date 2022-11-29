package OutputBoundary;
import DataStructures.UpdatedDebts;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface UpdatePaymentBalanceBoundaryOut {

    /**
     * Prepares and returns the information changed by the use case to the view.
     * @param updatedDebts the data structure with the updated list of debts for the group.
     * @return an UpdatedDebts object containing the updated information of the list of debts.
     */
    UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts);

    /**
     * Returns the information from an error in the form of an error message to the view.
     * @param updatedDebts contains the error message raised.
     * @return the data structure containing the error information.
     */
    UpdatedDebts prepareFailView(UpdatedDebts updatedDebts);
}
