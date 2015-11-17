package tutorial.core.repositories;

import tutorial.core.models.entities.OrderDetail;

import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface OrderDetailRepo {
    public OrderDetail findOrderDetail(Long id); // Returns the OrderDetail or null if it can't be found
    public OrderDetail deleteOrderDetail(Long id); // Deletes the found OrderDetail or returns null if it can't be found

    /**
     * @param id the id of the OrderDetail to updateOrderDetail
     * @param data the OrderDetail containing the data to be used for the updateOrderDetail
     * @return the updated OrderDetail or null if the OrderDetail with the id cannot be found
     */
    public OrderDetail updateOrderDetail(Long id, OrderDetail data);

    public OrderDetail createOrderDetail(OrderDetail data);

    public List<OrderDetail> findByOrderId(Long orderId);
}
