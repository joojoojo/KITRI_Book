package Book.book_rental.service;

import Book.book_rental.domain.User;
import Book.book_rental.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setUser_email("abc");

        //when
        Long saveUser_id = userService.join(user);

//      System.out.println(userRepository.findOne(saveUser_id));

        //then
       Assert.assertEquals(user, userRepository.findOne(saveUser_id));
    }

    @Test(expected = IllegalStateException.class)
    public void Duplicate_exception() throws Exception {
        //given
        User user1 = new User();
        user1.setUser_email("email1");
        User user2 = new User();
        user2.setUser_email("email1");

        //when
        userService.join(user1);
        userService.join(user2); //예외가 발생해야한다.
//        try {
//        } catch (IllegalStateException e){
//            return;
//        } @Test어노테이션에서 expected를 사용했기 때문에 생략 가능

        //then
        fail("예외가 발생했습니다.");
    }

}