package tutorial.core.services;

import tutorial.core.models.entities.User;
import tutorial.core.models.entities.Order;
import tutorial.core.services.util.UserList;
import tutorial.core.services.util.OrderList;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface UserService {
    public User findUser(Long id);
    public User createUser(User data);
    public Order createOrder(Long UserId, Order data);
    public OrderList findOrdersByUser(Long UserId);
    public UserList findAllUsers();
    public User findByUserName(String name);
    public User findByUserEmail(String email);
}
