package DataStructures;

public class RegisterCredentials {
    /**
     * Data structure modeling details of new user registering.
     */
    private final String username;
    private final String password1;
    private final String password2;

    public RegisterCredentials(String username, String password1, String password2) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return password
     */
    public String getPassword1() {
        return this.password1;
    }

    /**
     * @return repeat password
     */
    public String getPassword2() {
        return this.password2;
    }
}
