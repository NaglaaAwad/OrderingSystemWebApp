package tutorial.core.services;

import tutorial.core.models.entities.Order;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.services.util.OrderDetailList;
import tutorial.core.services.util.OrderList;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface OrderService {
    public OrderDetail createOrderDetail(Long orderId, OrderDetail data);

    public OrderList findAllOrders();

    public OrderDetailList findAllOrderDetails(Long orderId);

    public Order findOrder(Long id);
}
