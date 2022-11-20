package OutputBoundary;
import DataStructures.UpdatedLists;
public interface AddToPlanningBoundaryOut {
    /**
     * This function is called in the AddtoPlanning use case to pass the successful results as updated lists
     * so that the view can update accordingly.
     * @param lists a list that contains the updated planning list and the purchased list to pass to the view
     * @return This returns an instance of the UpdatedLists data structure that packages information on a group's current
     * planning and purchased lists to that it is usable for the view.
     */
    UpdatedLists displayLists(UpdatedLists lists);
    /**
     * This function is called in the AddtoPlanning use case to pass the failed results as a message
     * so that the view can update accordingly.
     * @param errorString a string that describes the failure that occurred in the AddtoPlanning use case.
     * @return This returns an instance of the UpdatedLists data structure that contains a failure description string.
     */
    UpdatedLists failErrorMessage(UpdatedLists errorString);
}
