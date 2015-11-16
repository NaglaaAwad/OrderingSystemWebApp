package tutorial.core.services.util;

import tutorial.core.models.entities.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public class ItemList {

    private List<Item> items = new ArrayList<Item>();

    public ItemList(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
