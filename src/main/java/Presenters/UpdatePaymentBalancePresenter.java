package Presenters;

import DataStructures.UpdatedDebts;
import OutputBoundary.UpdatePaymentBalanceBoundaryOut;

public class UpdatePaymentBalancePresenter implements UpdatePaymentBalanceBoundaryOut{

    @Override
    public UpdatedDebts prepareUpdatedDebtList(UpdatedDebts updatedDebts) {
        return updatedDebts;
    }
}
