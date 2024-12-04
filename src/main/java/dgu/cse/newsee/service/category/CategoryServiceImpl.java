package dgu.cse.newsee.service.category;

import dgu.cse.newsee.apiPayload.exception.UserException;
import dgu.cse.newsee.domain.entity.UserCategory;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.repository.UserCategoryRepository;
import dgu.cse.newsee.repository.UserRepository;
import dgu.cse.newsee.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final UserCategoryRepository userCategoryRepository;
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;

    @Autowired
    public CategoryServiceImpl(UserCategoryRepository userCategoryRepository, UserRepository userRepository, UserAccountService userAccountService) {
        this.userCategoryRepository = userCategoryRepository;
        this.userRepository = userRepository;
        this.userAccountService = userAccountService;
    }


    // 모든 관심분야 가져오기
    @Override
    public List<Category> getAllCategories() {
        return Arrays.asList(Category.values());
    }

    // 사용자의 관심분야 가져오기
    @Override
    public List<Category> getUserCategories(String token) {
        Long userId = userAccountService.getUserIdFromToken(token);

        // 엔티티에서 카테고리 이름만 추출
        return userCategoryRepository.findByUserId(userId).stream()
                .map(userCategory -> Category.fromName(userCategory.getCategory()))
                .collect(Collectors.toList());
    }

    // 사용자의 관심분야 수정하기
    @Override
    @Transactional
    public void updateUserCategories(String token, List<Category> categories) {
        Long userId = userAccountService.getUserIdFromToken(token);

        // 기존 관심분야 삭제
        userCategoryRepository.deleteByUserId(userId);

        // 새로운 관심분야 저장
        List<UserCategory> userCategories = categories.stream()
                .map(category -> new UserCategory(userId, category.name()))
                .collect(Collectors.toList());

        userCategoryRepository.saveAll(userCategories);
    }
}
