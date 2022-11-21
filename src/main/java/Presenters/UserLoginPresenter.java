package Presenters;

import OutputBoundary.UserLoginBoundaryOut;

public class UserLoginPresenter implements UserLoginBoundaryOut {

    /**
     * @param success if the login was successful
     * @return if the login was successful
     */
    @Override
    public boolean success(boolean success) {

        return success;
    }
}
