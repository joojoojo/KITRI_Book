package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "Booked")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booked {

    @Id
    @GeneratedValue
    @Column(name = "booked_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;


    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private List<Book> books = new ArrayList<>();

    public void alreadyBooked() {
        throw new IllegalStateException("도서를 예약한 사람이 있습니다.");
    }

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user_id = user;
        user.getBooked().add(this);
    }

    // 대여 한 책 리스트 booked에 book 추가
    public void addBook(Book book) {
        books.add(book);
        book.setBooked(this);
    }

    //==생성 메서드==//
    public static Booked createBooked(User user, Book... books) {
        Booked booked = new Booked();
        booked.setUser(user);
        for (Book book : books) {
            booked.addBook(book);
        }

        return booked;
    }

}
