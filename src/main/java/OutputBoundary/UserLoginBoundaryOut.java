package OutputBoundary;

import DataStructures.LoggedInInfo;

public interface UserLoginBoundaryOut {

    LoggedInInfo successfulLogin(LoggedInInfo info);

    LoggedInInfo failedLogin(LoggedInInfo fail);
}
