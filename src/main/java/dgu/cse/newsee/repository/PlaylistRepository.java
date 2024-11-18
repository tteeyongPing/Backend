package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<List<Playlist>> findAllByUserId(Long userId);
    Optional<Playlist> findById(Long playlistId);
    List<Playlist> findByNameContainingOrUser_NameContaining(String name, String userName);
}
