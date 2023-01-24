package Book.book_rental.repository;

import Book.book_rental.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findByEmail(String user_email){
        return em.createQuery("select u from User u where m.user_email = :user_email", User.class)
                .setParameter("user_email", user_email)
                .getResultList();
    }

}
