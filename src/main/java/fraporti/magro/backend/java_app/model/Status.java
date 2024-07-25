package fraporti.magro.backend.java_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status {
  @Id
  @Column(name = "id_status")
  private Long idStatus;

  @Column(name = "status")
  private String status;
}
