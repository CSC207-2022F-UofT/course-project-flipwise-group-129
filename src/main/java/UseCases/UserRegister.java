/**
 * Use case for registering a new user
 *
 */
package UseCases;
import Entities.*;

import java.util.ArrayList;
import java.util.Objects;

public class UserRegister {

    public boolean UserRegister(String username, String password1, String password2) {
        if (usernameAvailable(username) & passwordsMatch(password1, password2)) {
            // create a new user in the database
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Group> initializeGroup() {
        /**
         *
        */
        return new ArrayList<Group>();
    }

    public boolean usernameAvailable(String username) {
        /**
         * Checks if a username is available.
         *
         * @param username the username to check if it is available
         * @return boolean if the username is available
        */
        return DataAccessInterface.entityIdExists(username);
    }

    public boolean passwordsMatch(String password1, String password2) {
        /**
         * Checks if two passwords.
         *
         * @param password1 user types in the password
         * @param password2 user repeats the same password
         * @return boolean if the two passwords match
         */
        return Objects.equals(password1, password2);
    }

}
