package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Book_Categories {
    @Id
    @GeneratedValue
    @Column(name="categoty_id")
    private Long id;

    private String category_name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Book_Category_Middle", // book_category_middle 테이블과 조인
            joinColumns = @JoinColumn(name = "category_middle_id"), // book_category_middle 테이블에 있는 pk키와 외래키 설정
            inverseJoinColumns = @JoinColumn(name = "book_id")) // book 테이블에 있는 pk키와 외래키 설정
    private List<Book> bookCategoriesList = new ArrayList<>();


    //==연관관계 메서드==//
    public void setBook(Book book){
        bookCategoriesList.add(book);
        book.setBookCategories(this);
    }

}
