package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.Item;
import tutorial.core.models.entities.OrderDetail;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class OrderDetailResource extends ResourceSupport {
    private Item item;
    private int quantity;
    private double totalPrice;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDetail toOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setItem(item);
        orderDetail.setQuantity(quantity);
        orderDetail.setTotalPrice(item.getPrice() * quantity);
        return orderDetail;
    }
}
