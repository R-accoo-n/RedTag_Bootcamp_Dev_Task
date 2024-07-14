package com.koltok.redtag_bootcamp_dev_task.controller;

import com.koltok.redtag_bootcamp_dev_task.model.Author;
import com.koltok.redtag_bootcamp_dev_task.model.Book;
import com.koltok.redtag_bootcamp_dev_task.service.AuthorService;
import com.koltok.redtag_bootcamp_dev_task.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book-form";
    }

    @PostMapping
    public String saveBook(@ModelAttribute Book book, @RequestParam Long authorId) {
        Optional<Author> author = authorService.getAllAuthors().stream().filter(a -> a.getId().equals(authorId)).findFirst();
        if (author.isPresent()) {
            book.setAuthor(author.get());
            bookService.saveBook(book);
            return "redirect:/books";
        } else {
            return "redirect:/books/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book-form";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book updatedBook, @RequestParam Long authorId) {
        Optional<Book> optionalBook = bookService.getBookById(updatedBook.getId());
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setTitle(updatedBook.getTitle());

            Optional<Author> author = authorService.getAllAuthors().stream().filter(a -> a.getId().equals(authorId)).findFirst();
            if (author.isPresent()) {
                existingBook.setAuthor(author.get());
                bookService.saveBook(existingBook);
                return "redirect:/books";
            } else {
                return "redirect:/books/edit/" + updatedBook.getId();
            }
        } else {
            return "redirect:/books";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String title, @RequestParam String author, Model model) {
        List<Book> books;
        if (!title.isEmpty()) {
            books = bookService.searchBooksByTitle(title);
        } else {
            books = bookService.searchBooksByAuthor(author);
        }
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/export")
    public void exportBooksToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=books.csv");
        PrintWriter writer = response.getWriter();
        writer.println("ID,Title,Author");

        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            writer.println(book.getId() + "," + book.getTitle() + "," + book.getAuthor().getName());
        }

        writer.flush();
        writer.close();
    }
}