package fraporti.magro.backend.java_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fraporti.magro.backend.java_app.model.Book;
import fraporti.magro.backend.java_app.model.Interaction;
import fraporti.magro.backend.java_app.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return this.bookService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Book book) {
        return this.bookService.create(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        return this.bookService.update(id, book);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.bookService.delete(id);
    }

    @GetMapping("/byRating")
    public ResponseEntity<?> getHighestRated() {
        return this.bookService.getHighestRated();
    }

    @PostMapping("/saveInteraction")
    public ResponseEntity<?> saveInteraction(@RequestBody Interaction interaction) {
        this.bookService.saveInteraction(interaction);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookmarked/{id}")
    public ResponseEntity<?> getBookmarked(@PathVariable Long id) {
        System.out.println("get bookmarked id: " + id);
        return this.bookService.getBookmarkeds(id);
    }
}
