package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.Item;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class ItemResource extends ResourceSupport {

    private String name;
    private int quantity;
    private String category;
    private String image;
    private Double price;
    private Date dateAdded ;
    private int status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Item toItem() {
        Item item = new Item();
        item.setName(name);
        item.setQuantity(quantity);
        item.setCategory(category);
        item.setImage(image);
        item.setPrice(price);
        item.setDateAdded(dateAdded);
        item.setStatus(status);
        return item;
    }
}
