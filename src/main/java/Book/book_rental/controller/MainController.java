package Book.book_rental.controller;

import Book.book_rental.UserInfo;
import Book.book_rental.domain.Book;
import Book.book_rental.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Autowired
    BookService bookService;


    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("session : " + session);
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);

        // 모든 book을 찾아서 모델에 추가
        List<Book> books = bookService.findBook();
        model.addAttribute("books", books);

        return "main/main";
    }
}
