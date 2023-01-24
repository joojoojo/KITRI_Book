package Book.book_rental.service;

import Book.book_rental.domain.User;
import Book.book_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 회원가입
     * **/
    public Long join(User user){
        validateDuplicate(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicate(User user) {
        userRepository.findByEmail(user.getUser_email());
    }

    //회원 전체조회


}
