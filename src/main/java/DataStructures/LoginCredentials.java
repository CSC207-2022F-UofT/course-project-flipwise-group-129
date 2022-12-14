/**
 * Data structure modeling an existing user attempting to log in
 */
package DataStructures;

public class LoginCredentials {
    private final String username;
    private final String password;

    /**
     * Constructor for the data needs for login credentials
     *
     * @param username user's username
     * @param password user's password
     */
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return username
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * Return password
     */
    public String getPassword() {

        return this.password;
    }
}
