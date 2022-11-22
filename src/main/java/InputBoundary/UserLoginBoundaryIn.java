/**
 * Input boundary for login an existing user.
 */
package InputBoundary;

import DataStructures.LoggedInInfo;
import DataStructures.LoginCredentials;
import DataStructures.RegisterCredentials;

public interface UserLoginBoundaryIn {

    LoggedInInfo executeUserLogin(LoginCredentials credentials);

}
