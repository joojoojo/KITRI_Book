package Book.book_rental.repository;

import Book.book_rental.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository BookRepository;

    @Test
    @Transactional
//    @Rollback(false)
    public void testBook() throws Exception{
        //given
        User user = new User();
        user.setUsername("UserA");
        //when
        Long saveId = BookRepository.save(user);
        User findUser = BookRepository.find(saveId);

        //then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(findUser.getUsername()).isEqualTo(user.getUsername());

    }
}