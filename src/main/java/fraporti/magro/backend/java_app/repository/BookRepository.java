package fraporti.magro.backend.java_app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fraporti.magro.backend.java_app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b ORDER BY b.avgRating DESC")
    public List<Book> findByAvgRating(Pageable pageable);
}
