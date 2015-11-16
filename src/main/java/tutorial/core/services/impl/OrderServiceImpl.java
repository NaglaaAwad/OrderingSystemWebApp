package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Order;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.repositories.OrderDetailRepo;
import tutorial.core.repositories.OrderRepo;
import tutorial.core.services.OrderService;
import tutorial.core.services.exceptions.OrderNotFoundException;
import tutorial.core.services.util.OrderDetailList;
import tutorial.core.services.util.OrderList;


/**
 * Created by Naglaa on 10/19/2015.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public OrderDetail createOrderDetail(Long orderId, OrderDetail data) {
        Order order = orderRepo.findOrder(orderId);
        if(order == null)
        {
            throw new OrderNotFoundException();
        }
        OrderDetail entry = orderDetailRepo.createOrderDetail(data);
        entry.setOrder(order);
        return entry;
    }

    @Override
    public OrderList findAllOrders() {
        return new OrderList(orderRepo.findAllOrders());
    }

    @Override
    public OrderDetailList findAllOrderDetails(Long orderId) {
        Order order = orderRepo.findOrder(orderId);
        if(order == null)
        {
            throw new OrderNotFoundException();
        }
        return new OrderDetailList(orderId, orderDetailRepo.findByOrderId(orderId));
    }

    @Override
    public Order findOrder(Long id) {
        return orderRepo.findOrder(id);
    }
}
