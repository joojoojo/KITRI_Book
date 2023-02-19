package Book.book_rental.repository;

import Book.book_rental.domain.Book;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public void save(Book book){ //등록하려는 책이 있는지 확인
        if (book.getId() == null){
            em.persist(book);
        }
        else{
            em.merge(book);
        }
    }
    public Book findOne(Long id){
        return em.find(Book.class, id);
    }

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }



}