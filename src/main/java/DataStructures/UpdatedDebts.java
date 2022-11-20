package DataStructures;

import java.util.List;

public class UpdatedDebts {
    private final List<List<String>> updatedBalances;
    private String outcomeMessage = "Success";

    public UpdatedDebts(List<List<String>> updatedBalances) {
        this.updatedBalances = updatedBalances;
    }
    public UpdatedDebts(String message) {
        this.updatedBalances = null;
        this.outcomeMessage = message;
    }

    public List<String> getUpdatedBalances(){
        return this.updatedBalances;
    }

    public String getOutcomeMessage(){
        return this.outcomeMessage;
    }
}
