/**
 * Use case for login a user who already has an account
 */
package UseCases;
import DataAccessInterface.UserDataInterface;
import DataStructures.LoginCredentials;
import Entities.User;
import InputBoundary.UserLoginBoundaryIn;
import OutputBoundary.UserLoginBoundaryOut;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Objects;

public class UserLogin implements UserLoginBoundaryIn {

    UserLoginBoundaryOut outputBoundary;
    UserDataInterface dataAccess;

    public UserLogin(UserLoginBoundaryOut outputBoundary, UserDataInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    /**
     * Updates the outputBoundary as to the success for failure of a login attempt.
     *
     * @param credentials the login credentials that the user entered
     * @throws JsonProcessingException if there is an error parsing the JSON
     */
    @Override
    public void executeUserLogin(LoginCredentials credentials) throws JsonProcessingException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        outputBoundary.success(usernamePasswordMatch(username, password));
    }

    /**
     * Helper method to determine if the user exists and if the passwords match.
     *
     * @param username username entered by the user
     * @param password password entered by the user
     * @return if the login details are valid
     * @throws JsonProcessingException if there is an error parsing the JSON
     */
    private boolean usernamePasswordMatch(String username, String password) throws JsonProcessingException {
        if (dataAccess.userIdExists(username)) {
            String userDetails = dataAccess.userAsString(username);
            User user = User.fromString(userDetails);
            return Objects.equals(user.getPassword(), password);
        } else {
            return (false);
        }
    }
}
