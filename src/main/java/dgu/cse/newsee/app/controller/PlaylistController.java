package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.service.playlist.PlaylistService;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "🎶 플레이리스트", description = "플레이리스트 관련 API")
@RequestMapping("api/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserAccountService userAccountService;

    @Operation(summary = "내 플레이리스트 목록 가지고오기")
    @GetMapping("/list")
    public ApiResponse<?> getMyPlaylist(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        List<PlaylistDto.getPlaylistResponseDto> playlists = playlistService.getMyPlaylists(userId);
        return ApiResponse.onSuccess(Status.MY_PLAYLISTS_SUCCESS, playlists);
    }

    @Operation(summary = "내 플레이리스트에 뉴스 추가하기")
    @PostMapping("/news/add")
    public ApiResponse<?> addToPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistDto.updateNewsRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.addToPlaylist(userId, dto);
        return ApiResponse.onSuccess(Status.ADD_TO_PLAYLIST_SUCCESS, null);
    }

    @Operation(summary = "플레이리스트의 뉴스 삭제하기")
    @DeleteMapping("/news/remove")
    public ApiResponse<?> deleteFromPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistDto.updateNewsRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.deleteFromPlaylist(userId, dto);
        return ApiResponse.onSuccess(Status.DELETE_FROM_PLAYLIST_SUCCESS, null);
    }

    @Operation(summary = "플레이리스트 생성하기")
    @PostMapping("/create")
    public ApiResponse<?> createMyPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistDto.createRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.createMyPlaylist(userId, dto);
        return ApiResponse.onSuccess(Status.CREATE_PLAYLIST_SUCCESS, null);
    }

    @Operation(summary = "플레이리스트 이름/설명 수정하기")
    @PatchMapping("/edit")
    public ApiResponse<?> editPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistDto.updatePlaylistRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.editPlaylist(userId, dto);
        return ApiResponse.onSuccess(Status.EDIT_PLAYLIST_SUCCESS, null);
    }

    @Operation(summary = "플레이리스트 삭제하기")
    @DeleteMapping("/remove")
    public ApiResponse<?> deleteMyPlaylist(@RequestHeader("Authorization") String token, @RequestParam(value = "playlistId") Long playlistId){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.deleteMyPlaylist(userId, playlistId);
        return ApiResponse.onSuccess(Status.DELETE_PLAYLIST_SUCCESS, null);
    }

    @Operation(summary = "구독중인 플레이리스트 목록 가지고오기")
    @GetMapping("/subscribe/list")
    public ApiResponse<?> getSubscribePlaylist(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        List<PlaylistDto.getPlaylistResponseDto> playlists = playlistService.getSubscribePlaylist(userId);
        return ApiResponse.onSuccess(Status.MY_SUBSCRIBE_SUCCESS, playlists);
    }

    @Operation(summary = "플레이리스트 구독하기")
    @PostMapping("/subscribe")
    public ApiResponse<?> subscribePlaylist(@RequestHeader("Authorization") String token, @RequestParam(value = "playlistId") Long playlistId){
        Long userId = userAccountService.getUserIdFromToken(token);
        playlistService.subscribePlaylist(userId, playlistId);
        return ApiResponse.onSuccess(Status.SUBSCRIBE_SUCCESS, null);
    }

    @Operation(summary = "플레이리스트 ID로 플레이리스트 반환하기")
    @GetMapping("/{playlistId}")
    public ApiResponse<?> getPlaylistById(
            @RequestHeader("Authorization") String token,
            @PathVariable Long playlistId) {
        Long userId = userAccountService.getUserIdFromToken(token);
        PlaylistDto.getPlaylistResponseDto playlist = playlistService.getPlaylistById(userId, playlistId);
        return ApiResponse.onSuccess(Status.GET_PLAYLIST_SUCCESS, playlist);
    }

}
