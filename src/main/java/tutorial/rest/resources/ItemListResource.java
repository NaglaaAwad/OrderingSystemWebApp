package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/19/2015.
 */
public class ItemListResource extends ResourceSupport {
    private List<ItemResource> items = new ArrayList<ItemResource>();

    public List<ItemResource> getItems() {
        return items;
    }

    public void setItems(List<ItemResource> items) {
        this.items = items;
    }
}
