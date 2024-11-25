package dgu.cse.newsee.service.playlist;

import dgu.cse.newsee.apiPayload.exception.PlaylistException;
import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.Playlist;
import dgu.cse.newsee.domain.entity.PlaylistNews;
import dgu.cse.newsee.domain.entity.SubscribePlaylist;
import dgu.cse.newsee.repository.PlaylistNewsRepository;
import dgu.cse.newsee.repository.PlaylistRepository;
import dgu.cse.newsee.repository.SubscribePlaylistRepository;
import dgu.cse.newsee.service.news.NewsService;
import dgu.cse.newsee.service.user.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistNewsRepository playlistNewsRepository;
    private final UserAccountService userAccountService;
    private final SubscribePlaylistRepository subscribePlaylistRepository;
    private final NewsService newsService;

    @Override
    public List<PlaylistDto.getPlaylistResponseDto> getMyPlaylists(Long userId) {
        List<Playlist> playlists = playlistRepository.findAllByUserId(userId)
                .orElseThrow(() -> new PlaylistException.PlaylistNonExistsException("내 플레이리스트를 찾을 수 없습니다."));

        return playlists.stream().map(playlist -> new PlaylistDto.getPlaylistResponseDto(
                playlist.getId(),
                playlist.getName(),
                playlist.getDescription(),
                playlist.getUser().getId(),
                playlist.getUser().getName(),
                playlist.getPlaylistNews().stream().map(newsItem ->
                        new PlaylistDto.NewsDto(
                                newsItem.getNews().getId(),
                                newsItem.getNews().getTitle(),
                                newsItem.getNews().getDate(),
                                newsItem.getNews().getCompany())
                ).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }

    @Override
    public void addToPlaylist(Long userId, PlaylistDto.updateNewsRequestDto dto) {
        Playlist playlist = getPlaylistById(dto.getPlaylistId());
        isItMine(userId, dto.getPlaylistId());

        List<News> newsList = dto.getNewsIdList().stream()
                .map(newsService::getNewsById)
                .collect(Collectors.toList());

        newsList.forEach(news -> {
            if (playlistNewsRepository.findByPlaylistIdAndNewsId(playlist.getId(), news.getId()).isEmpty()) {
                PlaylistNews playlistNews = PlaylistNews.builder()
                        .playlist(playlist)
                        .news(news)
                        .build();
                playlistNewsRepository.save(playlistNews);
            }
        });
    }

    @Override
    public void deleteFromPlaylist(Long userId, PlaylistDto.updateNewsRequestDto dto) {
        Playlist playlist = getPlaylistById(dto.getPlaylistId());
        isItMine(userId, dto.getPlaylistId());
        if (dto.getNewsIdList() != null && !dto.getNewsIdList().isEmpty()) {
            dto.getNewsIdList().stream()
                    .map(newsId -> playlistNewsRepository.findByPlaylistIdAndNewsId(playlist.getId(), newsId)
                            .orElseThrow(() -> new PlaylistException.NewsNotFoundInPlaylistException(
                                    "존재하지 않는 newsId : " + newsId)))
                    .forEach(playlistNewsRepository::delete);
        }
    }

    @Override
    public void createMyPlaylist(Long userId, PlaylistDto.createRequestDto dto) {
        Playlist newPlaylist = Playlist.builder()
                .user(userAccountService.findById(userId).get())
                .name(dto.getPlaylistName())
                .description(dto.getDescription())
                .build();

        playlistRepository.save(newPlaylist);

        if (dto.getNewsIdList() != null && !dto.getNewsIdList().isEmpty()) {
            dto.getNewsIdList().forEach(newsId -> {
                News news = newsService.getNewsById(newsId);
                PlaylistNews playlistNews = PlaylistNews.builder()
                        .playlist(newPlaylist)
                        .news(news)
                        .build();
                playlistNewsRepository.save(playlistNews);
            });
        }
    }

    @Override
    public void editPlaylist(Long userId, PlaylistDto.updatePlaylistRequestDto dto) {
        Playlist playlist = getPlaylistById(dto.getPlaylistId());
        isItMine(userId, dto.getPlaylistId());

        playlist.setName(dto.getPlaylistName());
        playlist.setDescription(dto.getDescription());
        playlistRepository.save(playlist);

    }

    @Override
    public void deleteMyPlaylist(Long userId, Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        isItMine(userId, playlistId);
        playlistRepository.delete(playlist);
    }

    @Override
    public List<PlaylistDto.getPlaylistResponseDto> getSubscribePlaylist(Long userId) {
        List<SubscribePlaylist> subscribePlaylists = subscribePlaylistRepository.findAllByUserId(userId)
                .orElse(Collections.emptyList());

        if (subscribePlaylists.isEmpty()) {
            throw new PlaylistException.SubscribePlaylistNonExistsException("구독 중인 플레이리스트가 없습니다.");
        }

        return subscribePlaylists.stream()
                .map(subscribePlaylist -> {
                    Playlist playlist = subscribePlaylist.getPlaylist();
                    return new PlaylistDto.getPlaylistResponseDto(
                            playlist.getId(),
                            playlist.getName(),
                            playlist.getDescription(),
                            playlist.getUser().getId(),
                            playlist.getUser().getName(),
                            playlist.getPlaylistNews().stream().map(newsItem ->
                                    new PlaylistDto.NewsDto(
                                            newsItem.getNews().getId(),
                                            newsItem.getNews().getTitle(),
                                            newsItem.getNews().getDate(),
                                            newsItem.getNews().getCompany())
                            ).collect(Collectors.toList())
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public void subscribePlaylist(Long userId, Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        Optional<SubscribePlaylist> existingSubscription = subscribePlaylistRepository.findByUserIdAndPlaylistId(userId, playlistId);

        if(userId == playlist.getUser().getId()) { throw new PlaylistException.SubscribeMyPlaylistException("내 플레이리스트를 구독할 수 없습니다."); }
        if(existingSubscription.isPresent()) { throw new PlaylistException.AlreadySubscribedException("이미 구독 중인 플레이리스트입니다."); }

        SubscribePlaylist subscribePlaylist = SubscribePlaylist.builder()
                .user(userAccountService.findById(userId).get())
                .playlist(playlist)
                .build();

        subscribePlaylistRepository.save(subscribePlaylist);
    }

    // 해당 플레이리스트가 존재하는지 확인
    private Playlist getPlaylistById(Long playlistId){
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new PlaylistException.PlaylistNonExistsException("플레이리스트를 찾을 수 없습니다."));
    }

    // 내 플레이리스트가 맞는지 확인
    private void isItMine(Long userId, Long playlistId){
        Playlist playlist = getPlaylistById(playlistId);
        if (!playlist.getUser().getId().equals(userId)) {
            throw new PlaylistException.UnauthorizedPlaylistException("사용자에게 속하지 않은 플레이리스트입니다.");
        }
    }
}
