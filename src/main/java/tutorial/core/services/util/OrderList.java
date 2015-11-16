package tutorial.core.services.util;

import tutorial.core.models.entities.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public class OrderList {
    private List<Order> orders=new ArrayList<Order>();

    public OrderList(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
