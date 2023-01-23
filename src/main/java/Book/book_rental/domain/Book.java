package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Rental rental;

    @ManyToMany(mappedBy = "bookCategoriesList") // book_categories에 있는 manytomany 어노테이션과 연결
    List<Book_Categories> bookCategoriesList = new ArrayList<>(); // book_id 별 카테고리 리스트 가져오기

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
