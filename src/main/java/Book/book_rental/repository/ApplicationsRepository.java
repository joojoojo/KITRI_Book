package Book.book_rental.repository;

import Book.book_rental.domain.Applications;
import Book.book_rental.domain.Book;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ApplicationsRepository {
    private final EntityManager em;

    public void save(Applications applications){ //등록하려는 책이 있는지 확인
        if (applications.getId() == null){
            em.persist(applications);
        }
        else{
            em.merge(applications);
        }
    }

    public Applications findOne(Long id){
        return em.find(Applications.class, id);
    }

    public List<Applications> findAll(){
        return em.createQuery("select A from applications a", Applications.class).getResultList();
    }

}
