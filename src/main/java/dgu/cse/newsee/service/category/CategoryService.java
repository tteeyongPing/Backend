package dgu.cse.newsee.service.category;

import dgu.cse.newsee.apiPayload.exception.UserException;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.repository.UserCategoryRepository;
import dgu.cse.newsee.repository.UserRepository;
import dgu.cse.newsee.service.user.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service

public class CategoryService {

    private final UserCategoryRepository userCategoryRepository;
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;
    @Autowired
    public CategoryService(UserCategoryRepository userCategoryRepository, UserRepository userRepository, UserAccountService userAccountService) {
        this.userCategoryRepository = userCategoryRepository;
        this.userRepository = userRepository;
        this.userAccountService = userAccountService; // 필드 주입
    }

    // 모든 관심분야 가져오기
    public List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }

    // 사용자의 관심분야 가져오기
    public List<Category> getUserCategories(String token) {
        Long userId = userAccountService.getUserIdFromToken(token);
        if (!userRepository.existsById(userId)) {
            throw new UserException.UserNonExistsException("등록되지 않은 사용자입니다.");
        }
        return userCategoryRepository.findCategoriesByUserId(userId);
    }

    // 사용자의 관심분야 수정하기
    public void updateUserCategories(String token, List<Category> categories) {
        Long userId = userAccountService.getUserIdFromToken(token);
        if (!userRepository.existsById(userId)) {
            throw new UserException.UserNonExistsException("등록되지 않은 사용자입니다.");
        }

        userCategoryRepository.deleteByUserId(userId);


        categories.forEach(category ->
                userCategoryRepository.saveCategoryForUser(userId, String.valueOf(category.getId()))
        );

    }
}

