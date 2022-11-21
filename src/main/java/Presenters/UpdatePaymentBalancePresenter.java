package Presenters;

import DataStructures.UpdatedDebts;
import OutputBoundary.UpdatePaymentBalanceBoundaryOut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdatePaymentBalancePresenter implements UpdatePaymentBalanceBoundaryOut{

    @Override
    public UpdatedDebts prepareSuccessView(UpdatedDebts updatedDebts) {
        return updatedDebts;
    }

    @Override
    public UpdatedDebts prepareFailView(UpdatedDebts updatedDebts) {
        return updatedDebts;
    }
}
