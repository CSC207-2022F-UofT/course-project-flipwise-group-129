/**
 * Input boundary for login an existing user.
 */
package InputBoundary;

import DataStructures.LoggedInInfo;
import DataStructures.LoginCredentials;

public interface UserLoginBoundaryIn {

    LoggedInInfo executeUserLogin(LoginCredentials credentials);

}
