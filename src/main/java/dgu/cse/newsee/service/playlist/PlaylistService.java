package dgu.cse.newsee.service.playlist;

import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.domain.entity.Playlist;

import java.util.List;

public interface PlaylistService {
    List<PlaylistDto.getPlaylistResponseDto> getMyPlaylists(Long userId);

    void addToPlaylist(Long userId, PlaylistDto.updateNewsRequestDto dto);

    void deleteFromPlaylist(Long userId, PlaylistDto.updateNewsRequestDto dto);

    void createMyPlaylist(Long userId, PlaylistDto.createRequestDto dto);

    void editPlaylist(Long userId, PlaylistDto.updatePlaylistRequestDto dto);

    void deleteMyPlaylist(Long userId, Long playlistId);

    List<PlaylistDto.getPlaylistResponseDto> getSubscribePlaylist(Long userId);

    void subscribePlaylist(Long userId, Long playlistId);

    void subscribeCancelPlaylist(Long userId, Long playlistId);

    boolean getUserSubscribeStatus(Long userId, Long playlistId);
}
