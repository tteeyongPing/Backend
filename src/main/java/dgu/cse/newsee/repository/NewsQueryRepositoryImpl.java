package dgu.cse.newsee.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.QBookmark;
import dgu.cse.newsee.domain.entity.QNews;
import dgu.cse.newsee.domain.enums.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class NewsQueryRepositoryImpl implements NewsQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<News> findNewsListByCategory(String category){
        QNews news = QNews.news;
        return jpaQueryFactory
                .selectFrom(news)
                .where(news.category.eq(category))
                .fetch();
    }

    @Override
    public List<News> findNewsListByBookmark(Long userId) {
        QBookmark bookmark = QBookmark.bookmark;
        QNews news = QNews.news;

        return jpaQueryFactory.select(bookmark.news)
                .from(bookmark)
                .where(bookmark.user.id.eq(userId))
                .fetch();
    }
}