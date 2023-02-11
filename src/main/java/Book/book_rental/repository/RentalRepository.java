package Book.book_rental.repository;

import Book.book_rental.domain.Book;
import Book.book_rental.domain.Rental;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalRepository{

    private final EntityManager em;

    public void save(Rental rental){ //등록하려는 책이 있는지 확인
        if (rental.getId() == null){
            em.persist(rental);
        }
        else{
            em.merge(rental);
        }
    }

    public Rental findOne(Long id){
        return em.find(Rental.class, id);
    }

    public List<Rental> findAll() { return em.createQuery("select r from rental r", Rental.class).getResultList(); }

}