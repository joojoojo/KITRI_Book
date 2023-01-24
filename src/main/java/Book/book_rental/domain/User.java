package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;


@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String user_email;

    @OneToMany(mappedBy = "user_id")
    private List<Applications> applications = new ArrayList<>();

    @OneToMany(mappedBy = "user_id")
    private List<Rental> rentals = new ArrayList<>();


}