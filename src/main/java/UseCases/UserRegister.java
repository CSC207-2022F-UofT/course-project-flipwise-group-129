/**
 * Use case for registering a new user.
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

    /**
     * Creates a new user if the username is available and the passwords match.
     *
     * @param credentials a data structure containing the username, password, and repeat password
     * @throws IOException handles a file reading error
     */
    @Override
    public void executeUserRegister(RegisterCredentials credentials) throws IOException {
        String username = credentials.getUsername();
        String pw1 = credentials.getPassword1();
        String pw2 = credentials.getPassword2();
        if (usernameAvailable(credentials.getUsername()) & passwordsMatch(pw1, pw2)) {
            createUser(username, pw1);
            outputBoundary.success(true);
        } else {
            outputBoundary.success(false);
        }
    }

    /**
     * Creates a new user in the database. The user is a part of no groups.
     *
     * @param username the username to create the new user with
     * @param pw1 the password to create the new user with
     * @throws IOException handles file reading error
     */
    public void createUser(String username, String pw1) throws IOException {
        ArrayList<Group> noGroups = new ArrayList<>();
        User user = new User(username, pw1, noGroups);
        dataAccess.addorUpdateUser(username, user.toString());
    }

    /**
     * Checks if a username is available.
     *
     * @param username the username to check if it is available
     * @return if the username is available
     */
    public boolean usernameAvailable(String username) {
        return dataAccess.userIdExists(username);
    }

    /**
     * Checks if two passwords are the same.
     *
     * @param password1 user types in the password
     * @param password2 user repeats the same password
     * @return if the two passwords match
     */
    public boolean passwordsMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }
}
