package dgu.cse.newsee.service.search;

import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.app.dto.PlaylistDto;

import java.util.List;

public interface SearchService {
    List<NewsDto.NewsRequestDto> searchNews(String input);
    List<PlaylistDto.getPlaylistResponseDto> searchPlaylist(String input);
}
