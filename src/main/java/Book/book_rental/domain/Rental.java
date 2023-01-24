package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "Rental")
@Getter @Setter

public class Rental {
    @Id
    @GeneratedValue
    @Column(name = "rental_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private List<Book> book_id = new ArrayList<>();


    private LocalDateTime rental_date;

    private LocalDateTime rental_due;

    @PrePersist
    void preInsert() {
        this.rental_due = rental_date.plusDays(15);
    }

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user_id = user;
        user.getRentals().add(this);
    }

    public void addBook(Book book) {
        book_id.add(book);
        book.setRental(this);
    }




}