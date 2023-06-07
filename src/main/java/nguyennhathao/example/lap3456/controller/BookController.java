package nguyennhathao.example.lap3456.controller;


import jakarta.validation.Valid;
import nguyennhathao.example.lap3456.entity.Book;
import nguyennhathao.example.lap3456.services.BookService;
import nguyennhathao.example.lap3456.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired(required=false)
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books );
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book){
        bookService.addBook(book);
        return "redirect:/books";
    }

    //    @Autowired
//    private List<Book> books;
//
//    @GetMapping
//    public String listBooks(Model model){
//        model.addAttribute("books",books);
//        model.addAttribute("title","Book List");
//        return "book/list";
//    }
//
//    @GetMapping("/add")
//    public String addBookForm(Model model){
//        model.addAttribute("book", new Book());
//        return "book/add";
//    }
//
//    @PostMapping("/add")
//    public String addBook(@ModelAttribute("book") Book book){
//        books.add(book);
//        return "redirect:/books";
//    }
//
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Book editBook = bookService.getBookById(id);
        if(editBook != null){
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")Book updateBook, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        bookService.getAllBooks().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> {

                    bookService.updateBook(updateBook);
                });
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        Iterator<Book> iterator = bookService.getAllBooks().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getId() == id){
                iterator.remove();
                bookService.deleteBook(id);
                break;}
        }
        return "redirect:/books";
    }
}
