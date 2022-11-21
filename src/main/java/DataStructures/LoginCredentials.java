package DataStructures;

public class LoginCredentials {
    /**
     * Data structure modeling an existing user attempting to login
     */
    private final String username;
    private final String password;

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
