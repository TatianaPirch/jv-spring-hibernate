package mate.academy.spring.controller;

import java.util.Optional;

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

    @GetMapping("/rentBook")
    public String getAllBooksRentByUser(ModelMap model) {
        Optional<User> optionalUser = userService.findById(USER_ID);
        if (optionalUser.isEmpty()) {
            model.addAttribute("message", "User not found");
            return "errorPage";
        }
        model.put("books", libraryService.getBooksRentByUser(optionalUser.get()));
        return "listBooksRentByUser";
    }

    @GetMapping("/getBook")
    public String rentBook(@RequestParam("book_id") Long id, ModelMap model) {
        Optional<User> optionalUser = userService.findById(USER_ID);
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalUser.isEmpty() || optionalBook.isEmpty()) {
            model.addAttribute("message", "Failed to rent a book with id = " + id);
            return "errorPage";
        }
        model.addAttribute("book",
                libraryService.rentBook(optionalUser.get(), optionalBook.get()));
        return getAllBooksRentByUser(model);
    }

    @GetMapping("/returnBook")
    public String returnBook(@RequestParam("book_id") Long id, ModelMap model) {
        Optional<User> optionalUser = userService.findById(USER_ID);
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalUser.isEmpty() || optionalBook.isEmpty()) {
            model.addAttribute("message", "Failed to return a book with id = " + id);
            return "errorPage";
        }
        libraryService.returnBook(optionalUser.get(), optionalBook.get());
        return getAllBooksRentByUser(model);
    }
}
