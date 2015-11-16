package tutorial.core.services.util;

import tutorial.core.models.entities.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public class OrderDetailList {
    private Long orderId;
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

    public OrderDetailList(Long orderId, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public OrderDetailList(List<OrderDetail> orderDetail) {
        this.orderDetails = orderDetail;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }




}
