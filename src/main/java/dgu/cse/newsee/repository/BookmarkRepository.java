package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.Bookmark;
import dgu.cse.newsee.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<List<News>> findAllByUserId(Long userId);

    List<Bookmark> findAllByUserIdAndNewsIdIn(Long userId, List<Long> newsIds);
}
