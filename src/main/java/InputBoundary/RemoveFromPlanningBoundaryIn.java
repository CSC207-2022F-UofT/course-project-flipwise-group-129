package InputBoundary;

import DataStructures.PaymentInformation;
import DataStructures.UpdatedDebts;

public interface RemoveFromPlanningBoundaryIn {
    UpdatedDebts removeFromPlanningList(PaymentInformation paymentDetails);

}
