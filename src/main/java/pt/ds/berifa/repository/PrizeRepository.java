package pt.ds.berifa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pt.ds.berifa.domain.Prize;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long>, QuerydslPredicateExecutor<Prize> {
}
