package UseCases;
import Entities.*;

import java.util.ArrayList;
import java.util.Objects;

public class UserRegister {
    /**
     * Registers a new user. BChec
     *
     */

    public ArrayList<Group> initializeGroup() {
        return new ArrayList<Group>();
    }

    public boolean checkUsernameAvailable(String username) {
        ArrayList<String> all_users = User.getAllUsers();
        return all_users.contains(username);
    }

    public boolean checkPasswordsMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }

    public void createNewUser(String username, String password1, String password2) {
        if (this.checkUsernameAvailable(username) & this.checkPasswordsMatch(password1, password2)) {
            User user = new User(username, password1, initializeGroup());
        }
    }
}
