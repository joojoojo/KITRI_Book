package Book.book_rental.domain;

import Book.book_rental.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String Book_name;

    private String author;

    private String publisher;

    private int stock;

    private int rented_count;

    private int booked_count;

    @OneToMany(mappedBy = "book_id")
    private List<Rental> rentals = new ArrayList<>();

    @OneToMany(mappedBy = "book_id")
    private List<Booked> booked = new ArrayList<>();



    //==비지니스 로직==//
    /**
     * stock 증가/감소
     */
    public void addStock(int quantity) { // 재고 증가
        this.stock += quantity;
    }

    public void removeStock(int quantity) { //재고 감소
        int restStock = this.stock -quantity;
        if (restStock < 0){
            throw new NotEnoughStockException("need more stock"); // 재고가 음수가 될 수 없음
        }
        this.stock =- quantity;
    }

    /**
     * rented_count 수량
     */
    public void addRentCount(){
        this.rented_count += 1;
    }

    public void removeRentCount(){
        this.rented_count -= 1;
    }


    // 책 반납시 수량 적용
//    public void setRented_count(Rental rental){
//        this.rented_count = rental.getBooks().size();
//    }

    // 렌트된 책 개수
//    public void setBooked_count(Booked booked){
//        this.booked_count = booked.getBooks().size();
//    }

    public static Book createBook(Rental rental, Booked booked){
        Book book = new Book();
        book.setBook_name(book.Book_name);
        book.setAuthor(book.author);
        book.setPublisher(book.publisher);
        book.setStock(book.stock);
//        book.setRented_count(rental);
//        book.setBooked_count(booked);

        return book;
    }


}



// boolean 기능
//
//    @Convert(converter = BooleanToYNConverter.class)
//    private boolean rented;
//
//    @Convert(converter = BooleanToYNConverter.class)
//    private boolean booked;
//
//
//
//}
//@Converter
//class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
//    @Override
//    public String convertToDatabaseColumn(Boolean attribute) {
//        return (attribute != null && attribute) ? "Y" : "N";
//    }
//
//    @Override
//    public Boolean convertToEntityAttribute(String dbData) {
//        return "Y".equals(dbData);
//    }
//}
