/**
 * Controller for user register
 */
package InterfaceAdapters.Controllers;

import UseCaseLayer.DataStructures.RegisterCredentials;
import UseCaseLayer.InputBoundary.UserRegisterBoundaryIn;

public class UserRegisterController {
    private final UserRegisterBoundaryIn inputBoundary;

    /**
     * Controller for UserRegisterController
     *
     * @param inputBoundary the input boundary layer
     */
    public UserRegisterController(UserRegisterBoundaryIn inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Controller that converts data to the type required by the request model.
     *
     * @param username username
     * @param password1 password
     * @param password2 repeat password
     */
    public boolean controlUseCase(String username, String password1, String password2) {
        RegisterCredentials credentials = new RegisterCredentials(username, password1, password2);
        return (inputBoundary.executeUserRegister(credentials));
    }
}
