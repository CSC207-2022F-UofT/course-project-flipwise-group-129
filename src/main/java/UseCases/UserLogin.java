package UseCases;
import DataAccessInterface.UserDataInterface;


import java.util.*;

public class UserLogin implements UserDataInterface{
    public boolean loginSuccessful(String username, String password) {
        boolean exists = this.existsByUsername(username);
        if (exists & ) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public boolean existsByUsername(String username) {

    }
}
