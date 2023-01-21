package Book.book_rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("data", "value");
        return "main";
    }
}
