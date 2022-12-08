package UseCaseLayer.OutputBoundary;

import UseCaseLayer.DataStructures.UpdatedLists;


public interface AddPurchaseBoundaryOut {
    // Defining the functions that will be implemented by the AddPurchase presenter in the output boundary so that
    // the use case may call the presenter and update the view without violating clean architecture

    /**
     * Prepares and returns the information changed by the use case to the view
     * @param updatedLists the data structure with the new planning and purchased lists
     * @return a UpdatedLists object containing the updated information of the lists
     */
    UpdatedLists prepareSuccessViewInformation(UpdatedLists updatedLists);
    // This function uses the updated list data structure as a parameter as the new planning and purchase list are
    // what will be updated on the view after add purchase is called

    /**
     * Returns the information from an error in the form of an error message to the view
     * @param errorInformation contains the error message raised
     * @return the data structure containing the error information
     */
    UpdatedLists prepareFailViewInformation(UpdatedLists errorInformation);
}
