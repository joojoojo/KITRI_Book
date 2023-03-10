package Book.book_rental.controller;

import Book.book_rental.SessionManager;
import Book.book_rental.UserInfo;
import Book.book_rental.domain.User;
import Book.book_rental.repository.UserRepository;
import Book.book_rental.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.ast.spi.AbstractSqlAstTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
@SessionAttributes("user")

public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    SessionManager sessionManager;

    @Autowired
    UserRepository userRepository;
    @Resource
    private UserInfo userInfo;


    //    @GetMapping("/signup")
//    public String signup(Model model) {
//        log.info("signsignsign");
//        model.addAttribute("data", "value");
//        return "signup/signup";
//    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login/login");
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
            ModelAndView mav = new ModelAndView("signup/signup");
        return mav;
    }

//    @PostMapping("/signup/checkid")
//    public ResponseEntity<String> receiveData(@RequestParam("type") String type, @RequestParam("id") String id) {
//        // Do something with the received data
//        System.out.println("Type: " + type + " , ID: " + id);
//        return ResponseEntity.ok().body("Data received successfully");
//    }
    @PostMapping("signup/checkid")
    @ResponseBody
    public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {
        String result = userService.checkID(id, type);
        if(result != null && result.equals("0")){
            return 0;
        } else{
            return 1;
        }

    }

    @PostMapping("/signUp/post")
    public ResponseEntity<String> signup(@RequestParam("username") String username,
                                         @RequestParam("email") String email,
                                         @RequestParam("password") String password,
                                         @RequestParam("confirmPassword") String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new ResponseEntity<>("Password and Confirm Password do not match", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(username);
        user.setUser_email(email);
        user.setPassword(password);
        System.out.println(username);
        log.info(username);
        userService.join(user);
        return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
    }

@PostMapping("/login/post")
public ResponseEntity<String> login( @RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     HttpSession session) {

    User user = userService.login(email, password);
    if(user != null && user.getUser_email().equals(email)){

        session.setAttribute("userName", user.getUsername());

        return new ResponseEntity<>("Successfully Login", HttpStatus.OK);
    } else{
        return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
    }
}
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }


}
