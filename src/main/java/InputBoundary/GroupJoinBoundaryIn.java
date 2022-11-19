package InputBoundary;

import DataStructures.JoinGroupRequest;
import DataStructures.JoinedGroupInfo;

public interface GroupJoinBoundaryIn {

    JoinedGroupInfo joinGroup(JoinGroupRequest requestGroupInfo);
}
