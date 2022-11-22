package Controllers;

import DataStructures.LoggedInInfo;
import DataStructures.LoginCredentials;
import InputBoundary.UserLoginBoundaryIn;

public class UserLoginController {
    private final UserLoginBoundaryIn inputBoundary;

    /**
     * Constructor for UserLoginContoller.
     *
     * @param inputBoundary input boundary layer
     */
    public UserLoginController(UserLoginBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }
    /**
     * Controller that converts data to the type required by the request model when an existing user login.
     *
     * @param username the username
     * @param password the password
     * @return the outcome of the login (success or fail)
     */
    public LoggedInInfo controlUseCase(String username, String password) {
        LoginCredentials credentials = new LoginCredentials(username, password);
        return(inputBoundary.executeUserLogin(credentials));
    }
}
