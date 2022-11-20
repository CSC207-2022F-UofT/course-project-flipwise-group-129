package InputBoundary;

import DataStructures.RegisterCredentials;

import java.io.IOException;

public interface UserRegisterBoundaryIn {
    public boolean executeUserRegister(RegisterCredentials credentials) throws IOException;
}
