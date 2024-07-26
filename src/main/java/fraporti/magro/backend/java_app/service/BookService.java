package fraporti.magro.backend.java_app.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fraporti.magro.backend.java_app.dto.BookDTO;
import fraporti.magro.backend.java_app.dto.InteractionDTO;
import fraporti.magro.backend.java_app.model.Book;
import fraporti.magro.backend.java_app.model.Interaction;
import fraporti.magro.backend.java_app.repository.BookRepository;
import fraporti.magro.backend.java_app.repository.InteractionRepository;
import fraporti.magro.backend.java_app.util.Utils;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final InteractionRepository interactionRepository;

    public BookService(BookRepository bookRepository, InteractionRepository interactionRepository) {
        this.bookRepository = bookRepository;
        this.interactionRepository = interactionRepository;
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

    public ResponseEntity<?> getHighestRated() {
        List<BookDTO> books = Utils.mapAll(this.bookRepository.findByAvgRating(PageRequest.of(0, 5)), BookDTO.class);
        for (BookDTO book : books) {
            List<InteractionDTO> interactions = Utils.mapAll(this.interactionRepository.findByBook(book.getId()), InteractionDTO.class);
            book.setInteractions(interactions);
        }
        return ResponseEntity.ok(books);
    }

    public void saveInteraction(Interaction interaction) {
        this.interactionRepository.save(interaction);
    }

    public ResponseEntity<?> getBookmarkeds(Long idUser) {
        List<InteractionDTO> interactions = Utils.mapAll(this.interactionRepository.findByUser(idUser), InteractionDTO.class);
        System.out.println(interactions);
        return ResponseEntity.ok(interactions);
    }
}
