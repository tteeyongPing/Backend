package dgu.cse.newsee.service.memo;

public interface MemoService {
    void updateMemo(Long userId, Long newsId, String memoContent);
    void deleteMemo(Long userId, Long newsId);
}