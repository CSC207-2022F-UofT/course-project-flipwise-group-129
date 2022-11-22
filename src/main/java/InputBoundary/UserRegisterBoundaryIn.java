/**
 * Input boundary layer for registering a new user.
 */
package InputBoundary;

import DataStructures.RegisterCredentials;

public interface UserRegisterBoundaryIn {

    boolean executeUserRegister(RegisterCredentials credentials);
}
