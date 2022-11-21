package Presenters;

import DataStructures.RegisterCredentials;
import OutputBoundary.UserRegisterBoundaryOut;

public class UserRegisterPresenter implements UserRegisterBoundaryOut {
    public boolean success(boolean success) {
        /**
         * Presents a success or failed login to the view.
         * @param boolean success of login
         * @return success of login
         */
        return success;
    }
}
