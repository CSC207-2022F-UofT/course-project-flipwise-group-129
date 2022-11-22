/**
 * Presenter for a Registering a user, displaying the result.
 */
package Presenters;

import OutputBoundary.UserRegisterBoundaryOut;

public class UserRegisterPresenter implements UserRegisterBoundaryOut {

    /**
     * @param success if the register was successful
     * @return if the register was successful
     */
    public boolean success(boolean success) {
        return success;
    }
}
