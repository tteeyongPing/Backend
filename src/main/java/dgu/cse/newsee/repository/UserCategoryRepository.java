package dgu.cse.newsee.repository;

import dgu.cse.newsee.domain.enums.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository {
    List<Category> findCategoriesByUserId(Long userId);
    void updateUserCategories(Long userId, List<Category> categories);

    void deleteByUserId(Long userId);

    void saveCategoryForUser(Long userId, String id);
}
