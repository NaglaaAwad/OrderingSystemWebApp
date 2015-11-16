package tutorial.core.services;

import tutorial.core.models.entities.OrderDetail;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface OrderDetailService {
    public OrderDetail findOrderDetail(Long id);
    public OrderDetail deleteOrderDetail(Long id);
    public OrderDetail updateOrderDetail(Long id, OrderDetail data);
}
