package UseCases;
import UseCases.*;

import java.util.ArrayList;

public class UserLogin {
    public boolean loginSuccessful(String username, String password) {
        ArrayList<String> all_users= User.getAllUsers();
        if (!all_users.contains(username)) {
            return false;
        } else {
            // Need to get a user object from the username
            // Then get the password and make check it matches
        }
    }
}
