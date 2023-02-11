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

    @OneToMany(mappedBy = "user_id")
    private List<Booked> booked = new ArrayList<>();

    public User createUser(String username, String user_email){
        User user = new User();
        user.username = username;
        user.user_email = user_email;
        return user;
    }

    public void setUser(){
        this.username = username;
        this.user_email = user_email;
        setUsername(username);
        setUser_email(user_email);
    }



}