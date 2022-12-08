/**
 * Output boundary after the outcome of a login is produced by the use case.
 * Two methods, one for failed and other for successful login.
 */
package UseCaseLayer.OutputBoundary;

import UseCaseLayer.DataStructures.LoggedInInfo;

public interface UserLoginBoundaryOut {

    LoggedInInfo successfulLogin(LoggedInInfo info);

    LoggedInInfo failedLogin(LoggedInInfo fail);
}
