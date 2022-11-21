package Presenters;

import DataStructures.UpdatedLists;
import OutputBoundary.AddPurchaseBoundaryOut;


public class AddPurchasePresenter implements AddPurchaseBoundaryOut {

    /**
     * Prepares and returns the information changed by the use case to the view
     *
     * @param updatedLists the data structure with the new planning and purchased lists
     * @return a UpdatedLists object containing the updated information of the lists
     */
    @Override
    public UpdatedLists prepareSuccessViewInformation(UpdatedLists updatedLists) {
        // Provide the information to the view for updating
        return updatedLists;
    }

    /**
     * Returns the information from an error in the form of an error message to the view
     *
     * @param errorInformation contains the error message raised
     * @return the data structure containing the error information
     */
    @Override
    public UpdatedLists prepareFailViewInformation(UpdatedLists errorInformation) {
        // Provide the error message to the ui for updating
        return errorInformation;
    }

    //    speak to the view
}
