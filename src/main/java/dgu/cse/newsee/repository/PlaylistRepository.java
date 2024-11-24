package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<List<Playlist>> findAllByUserId(Long userId);
    Optional<Playlist> findById(Long playlistId);
    @Query("SELECT p FROM Playlist p JOIN p.user u WHERE p.name LIKE %:input% OR u.name LIKE %:input%")
    List<Playlist> findByNameContainingOrUser_NameContaining(@Param("input") String input);


}
