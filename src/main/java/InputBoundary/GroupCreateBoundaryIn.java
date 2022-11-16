package InputBoundary;

import DataStructures.CreatedGroupInfo;
import DataStructures.ProposedGroupInfo;

public interface GroupCreateBoundaryIn {
    CreatedGroupInfo createNewGroup(ProposedGroupInfo newGroupInfo);
}
