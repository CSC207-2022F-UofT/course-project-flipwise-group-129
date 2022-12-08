package InterfaceAdapters.Controllers;

import UseCaseLayer.DataStructures.PaymentInformation;
import UseCaseLayer.DataStructures.UpdatedDebts;
import UseCaseLayer.InputBoundary.UpdatePaymentBalanceBoundaryIn;
import java.util.List;

public class UpdatePaymentBalanceController {

    final UpdatePaymentBalanceBoundaryIn userInput;

    /**
     * Assigns values from the parameters to the class attribute variables.
     * @param input the UpdatePaymentBalanceBoundaryInput object which is input into the constructor.
     */
    public UpdatePaymentBalanceController(UpdatePaymentBalanceBoundaryIn input) {
        this.userInput = input;
    }

    /**
     * This function will be called from the main/view in order to control the use case updatePaymentBalance which
     * occurs whenever a user from a group purchases an item from the planning list. This controller packages all the
     * required information that the view sends forward into the PaymentDetails data structure, instantiates the
     * presenter, and passes all of this information to call the use case through the input boundary.
     * @param groupID String object representing the ID of a group.
     * @param user User object representing the user who purchased the item.
     * @param price Float object representing the price of the item purchased.
     * @param itemID String object representing the ID of the item purchased.
     * @return the information sent back by the presenter through the use case to go to the view layer as an
     * UpdatedDebts object.
     */
    public UpdatedDebts create(String groupID, String user, float price, String itemID, List<String> usersInPurchase) {
        PaymentInformation proposedInfo = new PaymentInformation(groupID, user, price, itemID, usersInPurchase);

        return userInput.updatePaymentBalance(proposedInfo);
    }
}
