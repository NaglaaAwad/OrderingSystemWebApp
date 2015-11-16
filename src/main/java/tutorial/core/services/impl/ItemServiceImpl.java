package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Item;
import tutorial.core.repositories.ItemRepo;
import tutorial.core.services.ItemService;
import tutorial.core.services.exceptions.ItemExistsException;
import tutorial.core.services.util.ItemList;

/**
 * Created by Naglaa on 10/19/2015.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item findItem(Long id) {

        return itemRepo.findItem(id);
    }

    @Override
    public Item createItem(Item data) {
        Item item = itemRepo.findItemByName(data.getName());
        if(item != null)
        {
            throw new ItemExistsException();
        }
        return itemRepo.createItem(data);
    }

    @Override
    public ItemList findAllItems() {

        return new ItemList(itemRepo.findAllItems());
    }

    @Override
    public Item findByName(String name) {
        return itemRepo.findItemByName(name);
    }

    @Override
    public Item findByCategory(String category) {
        return itemRepo.findItemByCategory(category);
    }

    @Override
    public Item updateItem(Long id, Item data) {
        return itemRepo.updateItem(id, data);
    }

    @Override
    public Item deleteItem(Long id) {
        return itemRepo.deleteItem(id);
    }

}
