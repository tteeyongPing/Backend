package dgu.cse.newsee.service.category;

import dgu.cse.newsee.domain.enums.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    List<Category> getUserCategories(String token);
    void updateUserCategories(String token, List<Category> categories);
    List<Category> getCategoriesByUserId(Long userId);
}
