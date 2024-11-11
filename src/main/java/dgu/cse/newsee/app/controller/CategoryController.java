package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.service.category.CategoryService;
import dgu.cse.newsee.domain.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 관심분야 리스트 가져오기
    @GetMapping("/list")
    public List<Category> getCategoryList() {
        return categoryService.getAllCategories();
    }

    // 내 관심분야 가져오기
    @GetMapping("/my")
    public List<Category> getMyCategories(@RequestParam Long userId) {
        return categoryService.getUserCategories(userId);
    }

    // 내 관심분야 등록/수정하기
    @PatchMapping("/edit")
    public void editMyCategories(@RequestParam Long userId, @RequestBody List<Integer> categoryIds) {
        List<Category> categories = categoryIds.stream().map(Category::fromId).toList();
        categoryService.updateUserCategories(userId, categories);
    }
}

