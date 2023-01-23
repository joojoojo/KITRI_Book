package Book.book_rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// import javax.persistence.id;
//import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Entity
@Table(name="Applications")
@Getter @Setter

public class Applications {

    @Id @GeneratedValue
    @Column(name="ap_book_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private String ap_book_name;

    private String ap_book_author;

    private String ap_book_publisher;

    private LocalDateTime ap_book_date;

    private ApplicationStatus status; //책 신청 상태 [진행중, 수락, 거절]
}
