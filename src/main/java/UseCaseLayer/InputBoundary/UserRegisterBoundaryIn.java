/**
 * Input boundary layer for registering a new user.
 */
package UseCaseLayer.InputBoundary;

import UseCaseLayer.DataStructures.RegisterCredentials;

public interface UserRegisterBoundaryIn {

    boolean executeUserRegister(RegisterCredentials credentials);
}
