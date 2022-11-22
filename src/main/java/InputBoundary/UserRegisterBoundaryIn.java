/**
 * Input boundary layer for registering a new user.
 */
package InputBoundary;

import DataStructures.RegisterCredentials;
import java.io.IOException;

public interface UserRegisterBoundaryIn {

    boolean executeUserRegister(RegisterCredentials credentials);
}
