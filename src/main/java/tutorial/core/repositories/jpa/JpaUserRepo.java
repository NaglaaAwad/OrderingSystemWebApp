package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.User;
import tutorial.core.repositories.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Naglaa on 10/18/2015.
 */
@Repository
public class JpaUserRepo implements UserRepo{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAllUsers() {
        Query query = em.createQuery("SELECT a FROM User a");
        return query.getResultList();
    }

    @Override
    public User findUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        Query query = em.createQuery("SELECT a FROM User a WHERE a.username=?1");
        query.setParameter(1, name);
        List<User> users = query.getResultList();
        if(users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = em.createQuery("SELECT a FROM User a WHERE a.email=?1");
        query.setParameter(1, email);
        List<User> users = query.getResultList();
        if(users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public User createUser(User data) {
        em.persist(data);
        return data;
    }
}
