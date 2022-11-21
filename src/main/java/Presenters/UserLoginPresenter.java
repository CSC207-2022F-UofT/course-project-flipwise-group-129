package Presenters;

import DataStructures.LoggedInInfo;
import OutputBoundary.UserLoginBoundaryOut;

public class UserLoginPresenter implements UserLoginBoundaryOut {

    /**
     * Constructor called when the login succeeds
     *
     * @param info if the login was successful
     * @return the user details
     */
    @Override
    public LoggedInInfo UserLoginBoundaryOut(LoggedInInfo info) {
        return info;
    }

    /**
     * Constructor called when the login fails
     *
     * @param fail to indicate the failure
     * @return false to the view
     */
    @Override
    public boolean UserLoginBoundaryOut(boolean fail) {
        return false;
    }
}
