package Controllers;

import DataAccessInterface.UserDataInterface;
import DataStructures.RegisterCredentials;
import Entities.User;
import InputBoundary.UserLoginBoundaryIn;
import InputBoundary.UserRegisterBoundaryIn;
import OutputBoundary.UserRegisterBoundaryOut;
import Presenters.UserRegisterPresenter;
import UseCases.UserRegister;

import java.io.IOException;

public class UserRegisterController {
    /**
     * Controller that converts data to the type required by the request model.
     *
     * @param username username
     * @param password1 password
     * @param password2 repeat password
     */
    public boolean controlUseCase(String username, String password1, String password2, UserRegisterBoundaryIn inputBoundary) {
        RegisterCredentials credentials = new RegisterCredentials(username, password1, password2);
        return (inputBoundary.executeUserRegister(credentials));
    }
}
