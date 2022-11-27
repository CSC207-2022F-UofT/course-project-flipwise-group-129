package DataStructures;

import java.util.List;

public class UpdatedDebts {
    /**
     * this class represents the output data structure to pass to the view when debts have been updated
     */
    private final List<List<String>> updatedBalances;
    private String outcomeMessage = "Success";

    /**
     * initiates a new type of UpdatedDebts in case of no error
     * @param updatedBalances the list of updated balances in the group
     */
    public UpdatedDebts(List<List<String>> updatedBalances) {
        this.updatedBalances = updatedBalances;
    }

    /**
     * initiates a new type of UpdatedDebts in case of an error
     * @param message the error message on what went wrong
     */
    public UpdatedDebts(String message) {
        this.updatedBalances = null;
        this.outcomeMessage = message;
    }

    /**
     * get the updatedBalances in the group
     * @return the updatedBalances in the group
     */
    public List<List<String>> getUpdatedBalances(){
        return this.updatedBalances;
    }

    /**
     * the outcome message of updating debts
     * @return the outcome message
     */
    public String getOutcomeMessage(){
        return this.outcomeMessage;
    }
}
