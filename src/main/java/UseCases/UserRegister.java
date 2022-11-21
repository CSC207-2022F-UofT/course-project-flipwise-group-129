package UseCases;
import Entities.*;
import DataAccessInterface.UserDataInterface;
import java.util.ArrayList;
import java.util.Objects;

public class UserRegister implements UserDataInterface{
    /**
     * Registers a new user. Preforms two checks:
     * 1) Username is not taken,
     * 2) Passwords match.
     * When both conditions are satisfied, a new user object is created and
     * the database is updated.
     */

    public ArrayList<Group> initializeGroup() {
        return new ArrayList<Group>();
    }

    public boolean checkPasswordsMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }

    public void createNewUser(String username, String password1, String password2) {
        if (this.existsByUsername(username) & this.checkPasswordsMatch(password1, password2)) {
            User user = new User(username, password1, initializeGroup());
            this.addUser(username, password1);
        }
    }

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }
}
