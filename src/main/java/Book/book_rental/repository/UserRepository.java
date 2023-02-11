package Book.book_rental.repository;

import Book.book_rental.domain.User;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
//    @PersistenceContext
    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long user_id){
        return em.find(User.class, user_id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findByEmail(String user_email){
        return em.createQuery("select u from User u where u.user_email = :user_email", User.class)
                .setParameter("user_email", user_email)
                .getResultList();
    }

}
