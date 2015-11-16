package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Item;
import tutorial.core.models.entities.User;
import tutorial.core.repositories.ItemRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Repository
public class JpaItemRepo implements ItemRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Item> findAllItems() {
        Query query = em.createQuery("SELECT a FROM Item a");
        return query.getResultList();
    }

    @Override
    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public Item findItemByName(String name) {
        Query query = em.createQuery("SELECT a FROM Item a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Item> items = query.getResultList();
        if(items.size() == 0) {
            return null;
        } else {
            return items.get(0);
        }
    }

    @Override
    public Item findItemByCategory(String category) {
        Query query = em.createQuery("SELECT a FROM Item a WHERE a.category=?1");
        query.setParameter(1, category);
        List<Item> items = query.getResultList();
        if(items.size() == 0) {
            return null;
        } else {
            return items.get(0);
        }
    }

    @Override
    public Item createItem(Item data) {
        em.persist(data);
        return data;
    }

    @Override
    public Item deleteItem(Long id) {
        Item entry = em.find(Item.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public Item updateItem(Long id, Item data) {
        Item entry = em.find(Item.class, id);
        entry.setName(data.getName());
        entry.setQuantity(data.getQuantity());
        entry.setCategory(data.getCategory());
        entry.setPrice(data.getPrice());
        entry.setImage(data.getImage());
        entry.setDateAdded(data.getDateAdded());
        entry.setStatus(data.getStatus());
        return entry;
    }
}
