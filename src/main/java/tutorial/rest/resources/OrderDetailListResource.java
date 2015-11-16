package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderDetailListResource extends ResourceSupport {
    private List<OrderDetailResource> orderDetails = new ArrayList<OrderDetailResource>();

    public List<OrderDetailResource> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResource> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
