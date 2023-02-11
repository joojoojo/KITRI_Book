package Book.book_rental.service;

import Book.book_rental.domain.Book;
import Book.book_rental.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }
    public List<Book> findBook(){
        return bookRepository.findAll();
    }

    public Book findOne(Long bookId){
        return bookRepository.findOne(bookId);
    }

    //책 전체 수량 - rented_count 값 return


}
