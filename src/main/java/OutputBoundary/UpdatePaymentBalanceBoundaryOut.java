package OutputBoundary;
import DataStructures.UpdatedDebts;

public interface UpdatePaymentBalanceBoundaryOut {
    UpdatedDebts prepareUpdatedDebtList(UpdatedDebts updatedDebts);
}
