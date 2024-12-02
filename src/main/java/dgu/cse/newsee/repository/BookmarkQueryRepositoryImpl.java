package dgu.cse.newsee.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import static dgu.cse.newsee.domain.entity.QBookmark.bookmark;

@Repository
@AllArgsConstructor
public class BookmarkQueryRepositoryImpl implements BookmarkQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByUserIdAndNewsId(Long userId, Long newsId){
        return queryFactory
                .selectOne()
                .from(bookmark)
                .where(
                        bookmark.user.id.eq(userId),
                        bookmark.news.id.eq(newsId)
                )
                .fetchFirst() != null;
    }
}
