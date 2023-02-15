package Book.book_rental.service;

import Book.book_rental.domain.Rental;
import Book.book_rental.domain.User;
import Book.book_rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션 안에서 데이터 변경이 일어날 수 있도록
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입
     * **/
    @Transactional
    public Long join(User user){
        String userName = user.getUsername();
        String user_email = user.getUser_email();
        String userPassword = user.getPassword();
        user = user.createUser(userName, user_email, userPassword);
        validateDuplicateUser(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
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
    public User findOne(Long user_id){
        return userRepository.findOne(user_id);
    }

    public String checkID(String email, String type) {
        if(type.equals("email")) {
            List<User> users = userRepository.findByEmail(email);
            if (users.isEmpty()) {
                return "0";
            }
            return "1";
        }

        return "0";
    }

//    @Transactional
//    public void joinUser(UserVo userVo){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        userVo.setUserPw(passwordEncoder.encode(userVo.getPassword()));
//        userVo.setUserAuth("USER");
//        userVo.setAppendDate(localTime);
//        userVo.setUpdateDate(localTime);
//        userMapper.saveUser(userVo);
//    }


}
