package Controllers;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.LoggedInInfo;
import DataStructures.LoginCredentials;
import InputBoundary.UserLoginBoundaryIn;
import OutputBoundary.UserLoginBoundaryOut;
import Presenters.UserLoginPresenter;
import UseCases.UserLogin;

public class UserLoginController {
    /**
     * Controller that converts data to the type required by the request model.
     *
     * @param username
     * @param password
     * @return
     */
    public LoggedInInfo controlUseCase(String username, String password, UserLoginBoundaryIn inputBoundary) {
        LoginCredentials credentials = new LoginCredentials(username, password);
        return(inputBoundary.executeUserLogin(credentials));
    }
}
