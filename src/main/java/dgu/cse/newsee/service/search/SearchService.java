package dgu.cse.newsee.service.search;

import dgu.cse.newsee.apiPayload.exception.SearchException;
import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.Playlist;
import dgu.cse.newsee.repository.NewsRepository;
import dgu.cse.newsee.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final NewsRepository newsRepository;
    private final PlaylistRepository playlistRepository;

    public List<PlaylistDto.NewsDto> searchNews(String input) {
        List<News> newsList = newsRepository.findByTitleContainingOrContentContaining(input, input);
        if (newsList.isEmpty()) {
            throw new SearchException.NewsNotFoundException("검색된 뉴스가 없습니다.");
        }
        return newsList.stream()
                .map(news -> new PlaylistDto.NewsDto(
                        news.getId(),
                        news.getTitle(),
                        news.getDate(),
                        news.getCompany()
                ))
                .collect(Collectors.toList());
    }

    public List<PlaylistDto.getPlaylistResponseDto> searchPlaylist(String input) {
        List<Playlist> playlists = playlistRepository.findByNameContainingOrUser_NameContaining(input, input);
        if (playlists.isEmpty()) {
            throw new SearchException.PlaylistNotFoundException("검색된 플레이리스트가 없습니다.");
        }
        return playlists.stream()
                .map(playlist -> new PlaylistDto.getPlaylistResponseDto(
                        playlist.getId(),
                        playlist.getName(),
                        playlist.getDescription(),
                        playlist.getUser().getId(),
                        playlist.getPlaylistNews().stream()
                                .map(playlistNews -> new PlaylistDto.NewsDto(
                                        playlistNews.getNews().getId(),
                                        playlistNews.getNews().getTitle(),
                                        playlistNews.getNews().getDate(),
                                        playlistNews.getNews().getCompany()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
