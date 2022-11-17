package InputBoundary;

import DataStructures.PaymentDetails;
import DataStructures.UpdatedDebts;

public interface RemoveFromPlanningBoundaryIn {
    UpdatedDebts removeFromPlanningList(PaymentDetails paymentDetails);

}
