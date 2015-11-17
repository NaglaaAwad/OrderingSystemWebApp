package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.OrderDetail;
import tutorial.core.repositories.OrderDetailRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Repository
public class JpaOrderDetailRepo implements OrderDetailRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public OrderDetail findOrderDetail(Long id) {
        return em.find(OrderDetail.class, id);
    }

    @Override
    public OrderDetail deleteOrderDetail(Long id) {
        OrderDetail entry = em.find(OrderDetail.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetail data) {
        OrderDetail entry = em.find(OrderDetail.class, id);
        entry.setItems(data.getItems());
       return entry;
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        Query query = em.createQuery("SELECT b FROM OrderDetail b WHERE b.order.id=?1");
        query.setParameter(1, orderId);
        return query.getResultList();
    }
}
