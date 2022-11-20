package DataStructures;

public class RegisterCredentials {
    private String username;
    private String password1;
    private String password2;

    public RegisterCredentials(String username, String password1, String password2) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword1() {
        return this.password1;
    }

    public String getPassword2() {
        return this.password2;
    }
}