package UseCases;
import DataStructures.ProposedGroupInfo;
import DataStructures.CreatedGroupInfo;
import Entities.Group;
import Entities.User;
import Entities.PlanningList;
import Entities.PurchaseList;
import InputBoundary.GroupCreateBoundaryIn;
import OutputBoundary.GroupCreateBoundaryOut;
public class GroupCreate implements GroupCreateBoundaryIn{
    final ProposedGroupInfo newGroupInfo;
    final GroupCreateBoundaryOut groupCreatePresenter;

}
