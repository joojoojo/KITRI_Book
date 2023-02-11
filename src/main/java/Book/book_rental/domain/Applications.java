package Book.book_rental.domain;

import Book.book_rental.domain.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// import javax.persistence.id;
//import org.springframework.data.annotation.Id;
import java.io.ObjectInputFilter;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name="Applications")
@Getter @Setter

public class Applications {

    @Id @GeneratedValue
    @Column(name="ap_book_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    private String ap_book_name;

    private String ap_book_author;

    private String ap_book_publisher;

    private LocalDateTime ap_book_date;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; //책 신청 상태 [진행중, 수락, 거절]

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user_id = user;
        user.getApplications().add(this);
    }

    //==생성 메서드==//
    public static Applications createApplications(User user){
        Applications applications = new Applications();
        applications.setUser(user);
        applications.setStatus(ApplicationStatus.PROCEEDING);
        return applications;

    }

    //==비지니스 로직==//
    /**
     * 신청 취소
     */

    public void deleteApplication(Long id){

    }
    public void cancel(){
        if (this.getStatus() == ApplicationStatus.ALLOW){
            throw new IllegalStateException("이미 수락된 책은 취소가 불가능 합니다.");
        }
    }

    public void returnExist(){
        if (this.getStatus() == ApplicationStatus.ALLOW || getStatus() == ApplicationStatus.PROCEEDING){
            throw new IllegalStateException("해당 도서는 신청된 도서입니다.");
        } if (getStatus() == ApplicationStatus.DENY){
            throw new IllegalStateException("해당 도서는 신청할 수 없습니다.");
        }
    }

}
