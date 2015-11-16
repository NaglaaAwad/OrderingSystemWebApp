package tutorial.core.models.entities;

import javax.persistence.*;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    @OneToOne
    private Item item;
    private int quantity;
    private double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItems() {
        return item;
    }

    public void setItems(Item items) {
        this.item = items;
    }

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
        return quantity * item.getPrice();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
