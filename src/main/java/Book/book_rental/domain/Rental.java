package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rental")
@Getter @Setter

public class Rental {
    @Id
    @GeneratedValue
    @Column(name = "rental_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book_id;


    private LocalDateTime rental_date;

    private LocalDateTime rental_due;

    @PrePersist
    void preInsert() {
        this.rental_due = rental_date.plusDays(15);
    }




}
