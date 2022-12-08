/**
 * Use case for registering a new user.
 */
package UseCaseLayer.UseCases;
import UseCaseLayer.DataAccessInterface.UserDataInterface;
import UseCaseLayer.DataStructures.RegisterCredentials;
import Entities.*;
import UseCaseLayer.InputBoundary.UserRegisterBoundaryIn;
import UseCaseLayer.OutputBoundary.UserRegisterBoundaryOut;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRegister implements UserRegisterBoundaryIn {
    final UserRegisterBoundaryOut outputBoundary;
    final UserDataInterface dataAccess;

    public UserRegister(UserRegisterBoundaryOut outputBoundary, UserDataInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    /**
     * Creates a new user if the username is available and the passwords match.
     *
     * @param credentials a data structure containing the username, password, and repeat password
     */
    @Override
    public boolean executeUserRegister(RegisterCredentials credentials){
        try {
            String username = credentials.getUsername();
            String pw1 = credentials.getPassword1();
            String pw2 = credentials.getPassword2();
            if (usernameAvailable(username) & passwordsMatch(pw1, pw2)) {
                createUser(username, pw1);
                return(outputBoundary.success(true));
            } else {
                return(outputBoundary.success(false));
            }
        } catch (IOException e) {
            return(outputBoundary.success(false));
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
        List<String> noGroups = new ArrayList<>();
        User user = new User(username, pw1, noGroups);
        try {
            dataAccess.addorUpdateUser(username, user.toString());
        } catch (ParseException e) {
            this.outputBoundary.success(false);
        }
    }

    /**
     * Checks if a username is available.
     *
     * @param username the username to check if it is available
     * @return if the username is available
     */
    public boolean usernameAvailable(String username) {
        try {
            return !dataAccess.userIdExists(username);
        } catch (IOException | ParseException e) {
            return false;
        }
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
