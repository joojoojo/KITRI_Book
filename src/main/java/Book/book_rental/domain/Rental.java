package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "Rental")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 딴데서 Rental 객체를 직접생성할 수 없음

public class Rental {
    @Id
    @GeneratedValue
    @Column(name = "rental_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private List<Book> books = new ArrayList<>();

    private LocalDateTime rental_date;

    private LocalDateTime rental_due;

    @Enumerated(EnumType.STRING)
    private RentalStatus status; //책 대여 상태 [대여중, 반납]

//    @PrePersist
//    void preInsert() {
//        this.rental_due = rental_date.plusDays(15);
//    }

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user_id = user;
        user.getRentals().add(this);
    }

    // 렌탈 한 책 리스트 books에 book 추가
    public void addBook(Book book) {
        books.add(book);
        book.setRentals(this);
    }


    //==생성 메서드==//
    public static Rental createRental(User user, Book... books) {
        Rental rental = new Rental();
        rental.setUser(user);
        for (Book book : books) {
            rental.addBook(book);
        }
        rental.setRental_date(LocalDateTime.now());
        rental.setRental_due(rental.rental_date.plusDays(15));
        rental.setStatus(RentalStatus.RENTALED);

        return rental;
    }
    // Member, Delivery, OrderItem... 이들은 주문을 생성하기 위해 필요한 값들이고,
    //status는 주문이 생성되면 기본적으로 ORDER로 설정되고
    //orderDate는 주문이 생성되는 시점의 시간으로 고정되기 때문에 인자로 받지 않는 것입니다.

    //==비지니스 로직==//
    public void removeReturn(){
        for (Book book : books){
            this.books.remove(book);
        }
    }

    public void returned(){
        if (this.status == RentalStatus.RETURN){
            this.removeReturn();
        }
    }





}