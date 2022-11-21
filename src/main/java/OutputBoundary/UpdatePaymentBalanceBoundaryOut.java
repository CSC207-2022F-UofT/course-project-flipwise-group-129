package OutputBoundary;
import DataStructures.UpdatedDebts;

public interface UpdatePaymentBalanceBoundaryOut {
    UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts);

    UpdatedDebts prepareFailView(UpdatedDebts updatedDebts);
}
