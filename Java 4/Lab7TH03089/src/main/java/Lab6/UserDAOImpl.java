package Lab6;

import jakarta.persistence.EntityManager;

interface UserDAO {
    User findByUsername(String username);
    void update(User user);
    void insert(User user);
}

public class UserDAOImpl implements UserDAO {
    private EntityManager em = JpaUtil.getEntityManager();

    public User findByUsername(String username) {
        return em.find(User.class, username);
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void insert(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
