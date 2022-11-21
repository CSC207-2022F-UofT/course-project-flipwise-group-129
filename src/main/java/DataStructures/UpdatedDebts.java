package DataStructures;
import java.util.Map;
import java.util.List;

public class UpdatedDebts {
    /*
    Returns the users involved in a purchase and the updated list of debts for the entire group.
     */
    public Map<String, List<Object>> updatedDebtsList;
    public String error;

    public UpdatedDebts(Map<String, List<Object>> udl) {
        this.updatedDebtsList = udl;
        this.error = null;
    }

    public UpdatedDebts(String error) {
        this.updatedDebtsList = null;
        this.error = error;
    }

    public Map<String, List<Object>> getUpdatedDebtsList() {
        return this.updatedDebtsList;
    }

}
