package Book.book_rental.controller;

import Book.book_rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserController {

    @Autowired
    UserService us; // 서비스 class

    // 아이디 중복 검사
    @PostMapping("login/checkid")
    @ResponseBody
    public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {

//    System.out.println("ajax 완료 : "+id);
//    String check = service.checkID(id);
//    System.out.println("중복검사 : "+a);
        String result = us.checkID(id, type);
        if(result != null && result.equals("1")){
            return 1;
        } else{
            return 0;
        }

    }



}
