package InputBoundary;

import DataStructures.RegisterCredentials;

import java.io.IOException;

public interface UserRegisterBoundaryIn {
    /**
     * Input boundary layer for registering a new user.
     *
     * @param credentials
     * @throws IOException
     */
    public boolean executeUserRegister(RegisterCredentials credentials) throws IOException;
}
