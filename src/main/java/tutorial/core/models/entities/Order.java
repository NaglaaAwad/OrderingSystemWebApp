package tutorial.core.models.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @ManyToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
