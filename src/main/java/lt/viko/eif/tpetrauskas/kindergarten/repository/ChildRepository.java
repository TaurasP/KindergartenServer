package lt.viko.eif.tpetrauskas.kindergarten.repository;

import lt.viko.eif.tpetrauskas.kindergarten.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {}
