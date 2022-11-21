package DataStructures;

public class RegisterCredentials {
    /**
     * Data structure containing details of new user registering.
     */
    private String username;
    private String password1;
    private String password2;

    public RegisterCredentials(String username, String password1, String password2) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getUsername() {
        /**
         * Returns username.
         *
         * @return username
         */
        return this.username;
    }

    public String getPassword1() {
        /**
         * Returns the user password
         *
         * @return password
         */
        return this.password1;
    }

    public String getPassword2() {
        /**
         * Returns the user's repeat password
         *
         * @return repeat password
         */
        return this.password2;
    }
}
