package Book.book_rental.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("data", "value");
        return "/login/login";
    }

}
