package Controllers;

import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;
import InputBoundary.UpdatePaymentBalanceBoundaryIn;

public class UpdatePaymentBalanceController {

    final UpdatePaymentBalanceBoundaryIn userInput;

    public UpdatePaymentBalanceController(UpdatePaymentBalanceBoundaryIn input) {
        this.userInput = input;
    }

    UpdatedDebts create(String groupID, String user, float price, String itemID) {
        PaymentDetails proposedInfo = new PaymentDetails(groupID, user, price, itemID);

        return userInput.updatePaymentBalance(proposedInfo);
    }
}
