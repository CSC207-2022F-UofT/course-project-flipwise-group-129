package Presenters;

import DataStructures.LoggedInInfo;
import OutputBoundary.UserLoginBoundaryOut;

public class UserLoginPresenter implements UserLoginBoundaryOut {
    @Override
    public LoggedInInfo successfulLogin(LoggedInInfo info) {
        return info;
    }

    @Override
    public LoggedInInfo failedLogin(LoggedInInfo info) {
        return info;
    }
}
