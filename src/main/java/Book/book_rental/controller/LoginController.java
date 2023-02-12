package Book.book_rental.controller;

import Book.book_rental.domain.User;
import Book.book_rental.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
@Slf4j
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        log.info("logloglog");
        model.addAttribute("data", "value");
        return "login/login";
    }
    @GetMapping("/signup")
    public String signup(Model model) {
        log.info("signsignsign");
        model.addAttribute("data", "value");
        return "signup/signup";
    }
    @PostMapping("/login/idcheck")
    public ResponseEntity<String> receiveData(@RequestParam("type") String type, @RequestParam("id") String id) {
        // Do something with the received data
        System.out.println("Type: " + type + " , ID: " + id);
        return ResponseEntity.ok().body("Data received successfully");
    }

    @PostMapping("/signUp")
    public String signUp(User user) {
        return "redirect:/login";
    }
    @PostMapping
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
        //user.setPassword(password);
        userService.join(user);
        return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
    }
}
