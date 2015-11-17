package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Order;
import tutorial.core.repositories.OrderRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Repository
public class JpaOrderRepo implements OrderRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Order createOrder(Order data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Order> findAllOrders() {
        Query query = em.createQuery("SELECT b from Order b");
        return query.getResultList();
    }

    @Override
    public Order findOrder(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findOrdersByUser(Long userId) {
        Query query = em.createQuery("SELECT b from Order b where b.owner.id=?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }

    @Override
    public List<Order> findOrdersByDate(Date orderDate) {
        Query query = em.createQuery("SELECT b from Order b where b.orderdate=?1");
        query.setParameter(1, orderDate);
        return query.getResultList();
    }
}
