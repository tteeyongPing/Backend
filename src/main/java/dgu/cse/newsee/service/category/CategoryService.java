package dgu.cse.newsee.service.category;

import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    private final UserCategoryRepository userCategoryRepository;

    @Autowired
    public CategoryService(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }

    // 모든 관심분야 가져오기
    public List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }

    // 사용자의 관심분야 가져오기
    public List<Category> getUserCategories(Long userId) {
        return userCategoryRepository.findCategoriesByUserId(userId);
    }

    // 사용자의 관심분야 수정하기
    public void updateUserCategories(Long userId, List<Category> categories) {
        // 기존 관심 분야 삭제
        userCategoryRepository.deleteByUserId(userId);

        // 새로운 관심 분야 추가
        categories.forEach(category -> userCategoryRepository.saveCategoryForUser(userId, category.getId()));
    }
}

