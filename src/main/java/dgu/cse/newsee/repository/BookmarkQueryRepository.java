package dgu.cse.newsee.repository;

public interface BookmarkQueryRepository {

    boolean existsByUserIdAndNewsId(Long userId, Long newsId);
}
