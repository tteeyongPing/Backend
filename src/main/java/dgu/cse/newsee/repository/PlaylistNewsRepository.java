package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.PlaylistNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistNewsRepository extends JpaRepository<PlaylistNews, Long> {
    Optional<PlaylistNews> findByPlaylistIdAndNewsId(Long playlistId, Long newsId);
}
