package Presenters;

import DataStructures.RegisterCredentials;
import OutputBoundary.UserRegisterBoundaryOut;

public class UserRegisterPresenter implements UserRegisterBoundaryOut {
    public boolean success(boolean success) {
        return success;
    }
}
