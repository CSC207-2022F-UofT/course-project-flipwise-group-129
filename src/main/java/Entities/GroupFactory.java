package Entities;

import java.util.Set;

public interface GroupFactory {
    Group create(String name, Set<User> users);

}
