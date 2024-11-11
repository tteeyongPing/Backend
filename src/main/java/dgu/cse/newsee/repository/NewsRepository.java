package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

}