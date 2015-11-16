package tutorial.core.repositories;

import tutorial.core.models.entities.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface OrderRepo {
    public Order createOrder(Order data);
    public List<Order> findAllOrders();
    public Order findOrder(Long id);
    public List<Order> findOrdersByUser(Long userId);
    public List<Order> findOrdersByDate(Date orderDate);
}
