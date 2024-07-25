package fraporti.magro.backend.java_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fraporti.magro.backend.java_app.model.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
  @Query("SELECT i FROM Interaction i WHERE i.user.idUser = ?1")
  public List<Interaction> findByUser(Long idUser);

  @Query("SELECT i FROM Interaction i WHERE i.book.idBook = ?1")
  public List<Interaction> findByBook(Long idBook);
}
