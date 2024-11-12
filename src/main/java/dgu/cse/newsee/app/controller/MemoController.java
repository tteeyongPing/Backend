package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.MemoDto;
import dgu.cse.newsee.service.memo.MemoService;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "📄 메모", description = "메모 관련 API")
@RequestMapping("/api/memo")
public class MemoController {

    private final MemoService memoService;
    private final UserAccountService userAccountService;

    @Operation(summary = "메모 생성/수정")
    @PatchMapping("/edit")
    public ApiResponse<?> updateMemo(@RequestHeader("Authorization") String token, @RequestBody MemoDto.MemoRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        memoService.updateMemo(userId, dto.getNewsId(), dto.getMemo());
        return ApiResponse.onSuccess(Status.MEMO_UPDATE_SUCCESS, null);
    }

    @Operation(summary = "메모 삭제")
    @DeleteMapping("/remove")
    public ApiResponse<?> deleteMemo(@RequestHeader("Authorization") String token, @RequestBody MemoDto.MemoDeleteRequestDto dto){
        Long userId = userAccountService.getUserIdFromToken(token);
        memoService.deleteMemo(userId, dto.getNewsId());
        return ApiResponse.onSuccess(Status.MEMO_DELETE_SUCCESS, null);
    }
}