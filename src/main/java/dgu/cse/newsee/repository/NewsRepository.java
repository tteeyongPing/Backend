package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleContainingOrContentContaining(String title, String content);
}
