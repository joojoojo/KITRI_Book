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

    @OneToMany(mappedBy = "book_id", cascade = CascadeType.ALL)
    private Rental rental;

    @ManyToMany(mappedBy = "bookCategoriesList", cascade = CascadeType.ALL) // book_categories에 있는 manytomany 어노테이션과 연결
    private Book_Categories bookCategories;

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
