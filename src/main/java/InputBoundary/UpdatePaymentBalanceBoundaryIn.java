package InputBoundary;

import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface UpdatePaymentBalanceBoundaryIn {
    /**
     * Updates the list of debts for a group after the purchase of an item has been made.
     * @param paymentDetails the data structure containing all the information required to update the debts in the
     *                       group.
     * @return the information that is prepared by the presenter to the controller.
     */
    UpdatedDebts updatePaymentBalance(PaymentInformation paymentDetails) throws IOException, ParseException;
}