package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.enums.Category;

import java.util.List;

public interface NewsQueryRepository {
    List<News> findNewsListByCategory(String category);

    List<News> findNewsListAll();

    List<News> findNewsListByBookmark(Long userId);

}
