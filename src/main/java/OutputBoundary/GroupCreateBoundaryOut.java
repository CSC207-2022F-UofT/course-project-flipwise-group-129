package OutputBoundary;
import DataStructures.CreatedGroupInfo;

public interface GroupCreateBoundaryOut {
    CreatedGroupInfo prepareSuccessView(CreatedGroupInfo createdGroupInfo);

    CreatedGroupInfo prepareFailView(CreatedGroupInfo createdGroupInfo);
}
