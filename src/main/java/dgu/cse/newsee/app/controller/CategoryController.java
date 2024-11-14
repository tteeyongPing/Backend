package dgu.cse.newsee.app.controller;


import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.service.category.CategoryService;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.app.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ApiResponse<Object> getCategoryList() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> data = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(Status.CATEGORY_LIST_SUCCESS, data);
    }

    // 내 관심분야 가져오기
    @GetMapping("/my")
    public ApiResponse<Object> getMyCategories(@RequestParam Long userId) {
        List<Category> categories = categoryService.getUserCategories(userId);
        List<CategoryDto> data = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(Status.CATEGORY_MY_SUCCESS, data);
    }

    // 내 관심분야 등록/수정하기
    @PatchMapping("/edit")
    public ApiResponse<Object> editMyCategories(@RequestParam Long userId, @RequestBody List<String> categoryNames) {
        List<Category> categories = categoryNames.stream().map(Category::fromName).toList();
        categoryService.updateUserCategories(userId, categories);

        return ApiResponse.onSuccess(Status.CATEGORY_EDIT_SUCCESS, null);
    }
}
