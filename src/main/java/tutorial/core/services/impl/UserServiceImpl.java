package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Order;
import tutorial.core.models.entities.User;
import tutorial.core.repositories.OrderRepo;
import tutorial.core.repositories.UserRepo;
import tutorial.core.services.UserService;
import tutorial.core.services.exceptions.*;
import tutorial.core.services.util.UserList;
import tutorial.core.services.util.OrderList;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public User findUser(Long id) {

        return userRepo.findUser(id);
    }

    @Override
    public User createUser(User data) {
        User user = userRepo.findUserByEmail(data.getEmail());
        if(user != null)
        {
            throw new UserExistsException();
        }
        return userRepo.createUser(data);
    }

    @Override
    public Order createOrder(Long userId, Order data) {

        User user = userRepo.findUser(userId);
        if(user == null)
        {
            throw new UserDoesNotExistException();
        }

        Order createdOrder = orderRepo.createOrder(data);

        createdOrder.setOwner(user);

        return createdOrder;
    }

    @Override
    public OrderList findOrdersByUser(Long userId) {
        User user = userRepo.findUser(userId);
        if(user == null)
        {
            throw new UserDoesNotExistException();
        }
        return new OrderList(orderRepo.findOrdersByUser(userId));
    }

    @Override
    public UserList findAllUsers() {

        return new UserList(userRepo.findAllUsers());
    }

    @Override
    public User findByUserName(String name) {
        return userRepo.findUserByName(name);
    }

    @Override
    public User findByUserEmail(String email) {
        return userRepo.findUserByEmail(email);
    }
}

