package pt.ds.berifa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.ds.berifa.domain.Associated;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, Long> {
}
