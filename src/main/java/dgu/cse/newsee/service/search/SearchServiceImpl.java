package dgu.cse.newsee.service.search;

import dgu.cse.newsee.apiPayload.exception.SearchException;
import dgu.cse.newsee.app.dto.NewsDto;
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
public class SearchServiceImpl implements SearchService {

    private final NewsRepository newsRepository;
    private final PlaylistRepository playlistRepository;

    @Override
    public List<NewsDto.NewsRequestDto> searchNews(String input) {
        List<News> newsList = newsRepository.findByTitleContainingOrContentContaining(input, input);
        if (newsList.isEmpty()) {
            throw new SearchException.NewsNotFoundException("검색된 뉴스가 없습니다.");
        }

        return newsList.stream()
                .map(news -> new NewsDto.NewsRequestDto(news.getId(), news.getTitle(), news.getDate(), news.getContent(), news.getReporter(), news.getCompany()))
                .collect(Collectors.toList());


    }

    @Override
    public List<PlaylistDto.getPlaylistResponseDto> searchPlaylist(String input) {
        List<Playlist> playlists = playlistRepository.findByNameContainingOrUser_NameContaining(input);
        if (playlists.isEmpty()) {
            throw new SearchException.PlaylistNotFoundException("검색된 플레이리스트가 없습니다.");
        }
        return playlists.stream()
                .map(playlist -> new PlaylistDto.getPlaylistResponseDto(
                        playlist.getId(),
                        playlist.getName(),
                        playlist.getDescription(),
                        playlist.getUser().getId(),
                        playlist.getUser().getName(),
                        playlist.getPlaylistNews().stream()
                                .map(playlistNews -> new PlaylistDto.NewsDto(
                                        playlistNews.getNews().getId(),
                                        playlistNews.getNews().getTitle(),
                                        playlistNews.getNews().getDate(),
                                        playlistNews.getNews().getCompany(),
                                        playlistNews.getNews().getCompany()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
