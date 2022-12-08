package UseCaseLayer.OutputBoundary;
import UseCaseLayer.DataStructures.CreatedGroupInfo;

public interface GroupCreateBoundaryOut {

    /*
     * Defining the functions that will be implemented by the GroupCreate Presenter through the boundary to
     * maintain clean architecture
     * to be implemented by the respective present
     */

    /**
     * prepares all the information to be displayed in the view
     * the name of the group
     * the users in it
     * @param createdGroupInfo contains all the information to be displayed
     * @return contains all the information to be displayed
     */
    CreatedGroupInfo prepareSuccessView(CreatedGroupInfo createdGroupInfo);

    /**
     * Alerts the view in case of a failure
     * @param createdGroupInfo contains the error message
     * @return the error messaged wrapped in an output data structure
     */
    CreatedGroupInfo prepareFailView(CreatedGroupInfo createdGroupInfo);
}
