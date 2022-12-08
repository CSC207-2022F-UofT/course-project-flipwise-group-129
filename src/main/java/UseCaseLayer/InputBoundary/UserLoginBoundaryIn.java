/**
 * Input boundary for login an existing user.
 */
package UseCaseLayer.InputBoundary;

import UseCaseLayer.DataStructures.LoggedInInfo;
import UseCaseLayer.DataStructures.LoginCredentials;

public interface UserLoginBoundaryIn {

    LoggedInInfo executeUserLogin(LoginCredentials credentials);

}
