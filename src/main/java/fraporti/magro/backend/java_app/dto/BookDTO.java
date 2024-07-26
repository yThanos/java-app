package fraporti.magro.backend.java_app.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookDTO {
  private Long id;
  private String title;
  private String author;
  private Integer year;
  private String genre;
  private String publisher;
  private Integer volumes;
  private Double avgRating;
  private String imgUrl;
  List<InteractionDTO> interactions;
}
