package fraporti.magro.backend.java_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_book_interaction")
public class Interaction {
  @Id
  @Column(name = "id_user_book_interaction")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "rating")
  private Integer rating;

  @Column(name = "bookmarked")
  private Boolean bookmarked;
  
  @Column(name = "comment")
  private String comment;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User user;

  @ManyToOne
  @JoinColumn(name = "id_book")
  private Book book;

  @ManyToOne
  @JoinColumn(name = "id_status")
  private Status status;
}
