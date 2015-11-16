package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.Order;

import java.util.Date;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderResource extends ResourceSupport {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order toOrder(){
        Order order = new Order();
        order.setDate(date);
        return order;

    }
}