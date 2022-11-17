package DataStructures;
import java.util.Map;
import java.util.List;

public class UpdatedDebts {
    /*
    Returns the users involved in a purchase and the updated list of debts for the entire group.
     */
    public Map<String, List<Object>> updatedDebtsList;

    public UpdatedDebts(Map<String, List<Object>> udl) {
        this.updatedDebtsList = udl;
    }

    public Map<String, List<Object>> getUpdatedDebtsList() {
        return this.updatedDebtsList;
    }

}
