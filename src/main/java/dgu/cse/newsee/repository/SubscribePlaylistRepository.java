package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.SubscribePlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscribePlaylistRepository extends JpaRepository<SubscribePlaylist, Long> {
    Optional<SubscribePlaylist> findByUserIdAndPlaylistId(Long userId, Long playlistId);

    Optional<List<SubscribePlaylist>> findAllByUserId(Long userId);

    void deleteByUserIdAndPlaylistId(Long userId, Long playlistId);
}
