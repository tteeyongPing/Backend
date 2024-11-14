package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.NewsMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoRepository extends JpaRepository<NewsMemo, Long> {
    Optional<NewsMemo> findByUserIdAndNewsId(Long userId, Long newsId);
}