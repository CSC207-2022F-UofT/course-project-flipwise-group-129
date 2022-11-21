/**
 * Use case for registering a new user.
 *
 */
package UseCases;
import DataAccessInterface.UserDataInterface;
import DataStructures.RegisterCredentials;
import Entities.*;
import InputBoundary.UserRegisterBoundaryIn;
import OutputBoundary.UserRegisterBoundaryOut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UserRegister implements UserRegisterBoundaryIn {
    UserRegisterBoundaryOut outputBoundary;
    UserDataInterface dataAccess;

    public UserRegister(UserRegisterBoundaryOut outputBoundary, UserDataInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public boolean executeUserRegister(RegisterCredentials credentials) throws IOException {
        /**
         * Creates a new user if the username is available and the passwords match.
         *
         * @param credentials a data structure containing the username, password, and repeat password
         * @return boolean if the user is registered successful
         */
        String username = credentials.getUsername();
        String pw1 = credentials.getPassword1();
        String pw2 = credentials.getPassword2();
        if (usernameAvailable(credentials.getUsername()) & passwordsMatch(pw1, pw2)) {
            createUser(username, pw1);
            outputBoundary.success(true);
            return true;
        } else {
            outputBoundary.success(false);
            return false;
        }
    }

    public void createUser(String username, String pw1) throws IOException {
        /**
         * Creates a new user in the database. The user is a part of no groups.
         * @param username the username to create the new user with
         * @param pw1 the password to create the new user with
        */
        ArrayList<Group> noGroups = new ArrayList<>();
        User user = new User(username, pw1, noGroups);
        dataAccess.addorUpdateUser(username, user.toString());
    }

    public boolean usernameAvailable(String username) {
        /**
         * Checks if a username is available.
         *
         * @param username the username to check if it is available
         * @return boolean if the username is available
        */
        return dataAccess.userIdExists(username);
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
