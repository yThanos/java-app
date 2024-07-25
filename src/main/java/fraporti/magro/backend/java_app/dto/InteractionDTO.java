package fraporti.magro.backend.java_app.dto;

import lombok.Data;

@Data
public class InteractionDTO {
  private Long id;
  private Integer rating;
  private Boolean bookmarked;
  private String comment;
  private UserDTO user;
  private BookDTO book;
  private StatusDTO status;
}
