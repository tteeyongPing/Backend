package dgu.cse.newsee.app.controller;


import dgu.cse.newsee.app.dto.ApiResponse;
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
    public ResponseEntity<Object> getCategoryList() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> data = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new ApiResponse(200, "SUCCESS", "관심분야를 가지고 왔습니다.", data));
    }

    // 내 관심분야 가져오기
    @GetMapping("/my")
    public ResponseEntity<Object> getMyCategories(@RequestParam Long userId) {
        List<Category> categories = categoryService.getUserCategories(userId);
        List<CategoryDto> data = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new ApiResponse(200, "SUCCESS", "내 관심분야를 가지고 왔습니다.", data));
    }

    // 내 관심분야 등록/수정하기
    @PatchMapping("/edit")
    public ResponseEntity<Object> editMyCategories(@RequestParam Long userId, @RequestBody List<String> categoryIds) {
        List<Category> categories = categoryIds.stream()
                .map(Category::fromId) // fromId 사용
                .toList();
        categoryService.updateUserCategories(userId, categories);

        return ResponseEntity.ok().body(new ApiResponse(200, "SUCCESS", "내 관심분야가 수정되었습니다.", null));
    }

}
