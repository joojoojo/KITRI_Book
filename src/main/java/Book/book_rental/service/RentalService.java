package Book.book_rental.service;

import Book.book_rental.domain.Rental;
import Book.book_rental.domain.User;
import Book.book_rental.domain.Book;
import Book.book_rental.repository.BookRepository;
import Book.book_rental.repository.RentalRepository;
import Book.book_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    //도서 반납

    @Transactional
    public void findReturned(Long RentalId){ // 컨트롤러에서 사용 에
        // Rental 엔티티 조회
        Rental rental  = rentalRepository.findOne(RentalId);
        // 도서 반납
        rental.returned();
    }

    @Transactional
    public Long rental(Long userId, Long bookId, int stock){
        User user = userRepository.findOne(userId);
        Book book = bookRepository.findOne(bookId);

        //대여 생성
        Rental rental = Rental.createRental(user, book);

        //대여 저장
        rentalRepository.save(rental);

        return rental.getId();
    }

    @Transactional
    public List<Rental> findAllRental(){
        return rentalRepository.findAll();
    }

}
