package fraporti.magro.backend.java_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fraporti.magro.backend.java_app.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    
}
