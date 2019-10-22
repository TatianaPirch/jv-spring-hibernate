package mate.academy.spring.controller;

import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.BookService;
import mate.academy.spring.service.LibraryService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rent")
public class LibraryController {
    private static final Long USER_ID = 1L;
    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/listBook")
    public String getAllBooksRentByUser(ModelMap model) {
        User user = userService.findById(USER_ID);
        model.put("books", libraryService.getBooksRentByUser(user));
        return "listBooksRentByUser";
    }

    @GetMapping("/getBook")
    public String rentBook(@RequestParam("book_id") Long id, ModelMap model) {
        User user = userService.findById(USER_ID);
        Book book = bookService.findById(id);
        model.addAttribute("book", libraryService.rentBook(user, book));
        return getAllBooksRentByUser(model);
    }

    @GetMapping("/returnBook")
    public String returnBook(@RequestParam("book_id") Long id, ModelMap model) {
        User user = userService.findById(USER_ID);
        Book book = bookService.findById(id);
        model.addAttribute("book", libraryService.returnBook(user, book));
        return getAllBooksRentByUser(model);
    }

}
