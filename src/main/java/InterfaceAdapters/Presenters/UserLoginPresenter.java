/**
 * Presenter after a user logins, displaying a failure or additional information if the login is successful
 */
package InterfaceAdapters.Presenters;

import UseCaseLayer.DataStructures.LoggedInInfo;
import UseCaseLayer.OutputBoundary.UserLoginBoundaryOut;

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
