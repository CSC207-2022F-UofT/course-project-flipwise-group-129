package UseCases;
import Entities.Group;
import Entities.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserRegister {

    public ArrayList intializeGroup() {
        return new ArrayList<Group>();
    }

    public boolean checkUsernameAvaliable(String username) {
        return groups.contains();
    }

    public boolean checkPasswordsMatch(String password1, String password2) {
        return Objects.equals(password1, password2);
    }

    public void createNewUser(String username, String password1, String password2) {
        if (this.checkUsernameAvaliable(username) & this.checkPasswordsMatch(password1, password2)) {
            User user = new User(username, password, intializeGroup());
        }
    }
}
