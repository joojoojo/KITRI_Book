package Book.book_rental.service;

import Book.book_rental.domain.User;
import Book.book_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션 안에서 데이터 변경이 일어날 수 있도록
public class UserService {

    @Autowired // spring이 spring been에 등록되어있는 Repository를 인젝션 해줌 (필드 인젝션)
    private UserRepository userRepository;

    /**
     * 회원가입
     * **/
    @Transactional
    public Long join(User user){
        validateDuplicate(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicate(User user) {
        List<User> findUsers = userRepository.findByEmail(user.getUser_email());
        if (! findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } // 같은 email로 동시에 두명이 회원가입을 하면 중복검사가 동시에 이루어져서 두명 다 중복검사를 통과함, 최후의 방어 체계를 구축해야함
    }

    //회원 전체조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //한명의 회원만 조회
    public User findOne(String email_id){
        return userRepository.findOne(email_id);
    }


}
