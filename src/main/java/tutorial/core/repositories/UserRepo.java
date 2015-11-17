package tutorial.core.repositories;

import tutorial.core.models.entities.User;

import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface UserRepo {
    public List<User> findAllUsers();
    public User findUser(Long id);
    public User findUserByName(String name);
    public User findUserByEmail(String email);
    public User createUser(User data);
}
