/**
 * Input boundary for login an existing user.
 */
package InputBoundary;

import DataStructures.LoginCredentials;
import java.io.IOException;

public interface UserLoginBoundaryIn {

    void executeUserLogin(LoginCredentials credentials) throws IOException;
}
