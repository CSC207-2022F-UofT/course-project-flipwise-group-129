package DataAccessInterface;

public interface UserDataInterface {

    void addUser(String username, String password);

    boolean existsByUsername(String username);

}
