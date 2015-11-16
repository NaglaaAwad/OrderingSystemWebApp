package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderListResource extends ResourceSupport {
    private List<OrderResource> orders = new ArrayList<OrderResource>();

    public List<OrderResource> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResource> orders) {
        this.orders = orders;
    }
}