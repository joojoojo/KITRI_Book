package Book.book_rental.controller;

import Book.book_rental.UserInfo;
import Book.book_rental.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserController {
//    public ResponseEntity<String> welcome(HttpSession session) {
//        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
//        if (userInfo != null) {
//            String userName = userInfo.getUserNm();
//            return new ResponseEntity<>("Welcome, " + userName, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
//        }
//    }

}
