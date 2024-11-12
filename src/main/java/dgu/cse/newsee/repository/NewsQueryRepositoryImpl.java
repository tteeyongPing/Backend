package dgu.cse.newsee.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.entity.QNews;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
                .limit(10)
                .fetch();
    }

    @Override
    public List<News> findNewsListAll() {
        QNews news = QNews.news;
        List<News> result = new ArrayList<>();

        List<String> categories = jpaQueryFactory
                .select(news.category)
                .distinct()
                .from(news)
                .fetch();

        for (String category : categories) {
            List<News> categoryNews = jpaQueryFactory
                    .selectFrom(news)
                    .where(news.category.eq(category))
                    .orderBy(news.date.desc())
                    .limit(10)
                    .fetch();
            result.addAll(categoryNews);
        }

        return result;
    }
}