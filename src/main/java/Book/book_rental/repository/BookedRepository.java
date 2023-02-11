package Book.book_rental.repository;

import Book.book_rental.domain.Book;
import Book.book_rental.domain.Booked;
import Book.book_rental.domain.Rental;
import Book.book_rental.service.BookService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookedRepository {
    private EntityManager em;

    public Booked findOne(Long id){
        return em.find(Booked.class, id);
    }

    public List<Booked> findAll(Booked booked) { return em.createQuery("select b from booked b", Booked.class).getResultList(); }


//
//    public Booked findByBookedId(int BookedId){
//        return em.find(Booked.class, BookedId);
//    }

}
