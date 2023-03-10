package Book.book_rental.service;

import Book.book_rental.domain.Rental;
import Book.book_rental.domain.User;
import Book.book_rental.exception.UserExistException;
import Book.book_rental.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Book.book_rental.exception.UserExistException;


import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션 안에서 데이터 변경이 일어날 수 있도록
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     **/

    @Transactional
    public Long join(User user) {
        String userName = user.getUsername();
        String user_email = user.getUser_email();
        //   String userPassword = user.getPassword();
        String userPassword = passwordEncoder.encode(user.getPassword());
        user = user.createUser(userName, user_email, userPassword);
        validateDuplicateUser(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }


    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByEmail(user.getUser_email());
        if (!findUsers.isEmpty()) {
            throw new UserExistException("이미 존재하는 회원입니다.");
        } // 같은 email로 동시에 두명이 회원가입을 하면 중복검사가 동시에 이루어져서 두명 다 중복검사를 통과함, 최후의 방어 체계를 구축해야함
    }

    //회원 전체조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //한명의 회원만 조회
    public User findOne(Long user_id) {
        return userRepository.findOne(user_id);
    }

    public String checkID(String email, String type) {
        if (type.equals("email")) {
            List<User> users = userRepository.findByEmail(email);
            if (users.isEmpty()) {
                return "0";
            }
            return "1";
        }

        return "0";
    }

    public User getUserByEmail(String user_email) {
        List<User> users = userRepository.findByEmail(user_email);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Autowired
    private HttpSession httpSession;
    public User login(String user_email, String password) {
        User user = getUserByEmail(user_email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//            httpSession.setAttribute("user", user);
            return user;
        }
        return null;
    }


    /**
     * user의 세션을 사용하여 메서들르 만들 때
     */
    public void myMethod() {
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            // 로그인한 사용자 정보 사용
        } else {
            // 로그인되어 있지 않은 상태
        }
    }




}
