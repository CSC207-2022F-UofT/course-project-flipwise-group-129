package InputBoundary;
import DataStructures.PlannedItemInfo;
import DataStructures.UpdatedLists;

import java.io.IOException;

public interface AddToPlanningBoundaryIn {
    UpdatedLists addPlanning(PlannedItemInfo item) throws IOException;

}
