package Book.book_rental.controller;

import Book.book_rental.UserInfo;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/main")
    public String main(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo != null) {
            model.addAttribute("userName", userInfo.getUserNm());
        }
        return "main";
    }
}
