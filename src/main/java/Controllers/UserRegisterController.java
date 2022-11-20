package Controllers;

import DataAccessInterface.UserDataInterface;
import DataStructures.RegisterCredentials;
import Entities.User;
import InputBoundary.UserRegisterBoundaryIn;
import OutputBoundary.UserRegisterBoundaryOut;
import Presenters.UserRegisterPresenter;
import UseCases.UserRegister;

import java.io.IOException;

public class UserRegisterController {
    public void controlUseCase(String username, String password1, String password2, UserRegisterBoundaryOut outputBoundary, UserDataInterface dataAccess) throws IOException {
        RegisterCredentials credentials = new RegisterCredentials(username, password1, password2);
        UserRegisterBoundaryOut out = new UserRegisterPresenter();
        UserRegisterBoundaryIn input = new UserRegister(outputBoundary, dataAccess);
        input.executeUserRegister(credentials);
    }
}
