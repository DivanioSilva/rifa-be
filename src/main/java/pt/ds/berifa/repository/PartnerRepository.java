package pt.ds.berifa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;
import pt.ds.berifa.domain.Partner;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>, QuerydslPredicateExecutor<Partner> {
    Optional<Partner> findByNif(long nif);
    Optional<Partner> findByIdAndNif(long id, long nif);
}
