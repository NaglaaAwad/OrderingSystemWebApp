package tutorial.core.services.util;

import tutorial.core.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public class UserList {
    private List<User> users = new ArrayList<User>();

    public UserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
