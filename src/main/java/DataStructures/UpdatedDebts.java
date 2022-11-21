package DataStructures;
import java.util.Map;
import java.util.List;

public class UpdatedDebts {
    /*
    Returns the users involved in a purchase and the updated list of debts for the entire group.
     */
    public Map<String, List<Object>> updatedDebtsList;
    public String error;

    /**
     * Assigns values from the parameters to the class attribute variables.
     * @param udl the HashMap containing the user who made the purchase's name, the user who owes money to the user who
     *            bought the item's name, and the price of the item.
     */
    public UpdatedDebts(Map<String, List<Object>> udl) {
        this.updatedDebtsList = udl;
        this.error = null;
    }

    /*
    Implementing the getter functions for the updated list of debts so that the presenter can use it to update the view.
     */

    /**
     * Assigns values from the parameters to the class attribute variables.
     * @param error the String when there is an error and the debts cannot be updated.
     */
    public UpdatedDebts(String error) {
        this.updatedDebtsList = null;
        this.error = error;
    }

    /**
     * @return list of all updated debts in a group represented as a HashMap.
     */
    public Map<String, List<Object>> getUpdatedDebtsList() {
        return this.updatedDebtsList;
    }

}
