package Book.book_rental.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class SignupController {

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("data", "value");
        return "signup/signup";
    }
}
