package fraporti.magro.backend.java_app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fraporti.magro.backend.java_app.model.Book;
import fraporti.magro.backend.java_app.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.bookRepository.findAll());
    }

    public ResponseEntity<?> getById(Long id) {
        return ResponseEntity.ok(this.bookRepository.findById(id));
    }

    public ResponseEntity<?> create(Book book) {
        return ResponseEntity.ok(this.bookRepository.save(book));
    }
    
    public ResponseEntity<?> update(Long id, Book book) {
        return ResponseEntity.ok(this.bookRepository.save(book));
    }

    public ResponseEntity<?> delete(Long id) {
        this.bookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
