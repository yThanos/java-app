package fraporti.magro.backend.java_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @Column(name = "id_permission")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
}
