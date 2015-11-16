package tutorial.core.services;

import tutorial.core.models.entities.Item;
import tutorial.core.services.util.ItemList;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface ItemService {
    public Item findItem(Long id);
    public Item createItem(Item data);
    public ItemList findAllItems();
    public Item findByName(String name);
    public Item findByCategory(String category);
    public Item updateItem(Long id, Item data);
    public Item deleteItem(Long id);
}
