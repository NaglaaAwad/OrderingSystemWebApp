package tutorial.core.repositories;

import tutorial.core.models.entities.Item;


import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
public interface ItemRepo {
    public List<Item> findAllItems();
    public Item findItem(Long id);
    public Item findItemByName(String name);
    public Item findItemByCategory(String category);
    public Item createItem(Item data);
    public Item deleteItem(Long id);
    public Item updateItem(Long id, Item data);
}
