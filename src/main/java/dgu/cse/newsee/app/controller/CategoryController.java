package dgu.cse.newsee.app.controller;


import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.exception.UserException;
import dgu.cse.newsee.service.category.CategoryService;
import dgu.cse.newsee.service.category.CategoryServiceImpl;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.app.dto.CategoryDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@Tag(name = "카테고리 API", description = "카테고리 관련 API")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
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
    public ApiResponse<Object> getMyCategories(@RequestHeader("Authorization") String token) {
        try {
            List<Category> categories = categoryService.getUserCategories(token);
            List<CategoryDto> data = categories.stream()
                    .map(CategoryDto::new)
                    .collect(Collectors.toList());

            return ApiResponse.onSuccess(Status.CATEGORY_MY_SUCCESS, data);
        }catch (UserException.UserNonExistsException ex){
            throw new UserException.UserNonExistsException("등록되지 않은 사용자입니다.");
        }
    }

    // 내 관심분야 등록/수정하기
    @PatchMapping("/edit")
    public ApiResponse<Object> editMyCategories(@RequestHeader("Authorization") String token, @RequestBody List<String> categoryIds) {
        try{
        // categoryIds를 Category 객체로 변환
        List<Category> categories = categoryIds.stream()
                .map(Category::fromStringId) // fromId 메서드가 String을 받아 Category 반환
                .toList();

        // Service 호출
        categoryService.updateUserCategories(token, categories);
        return ApiResponse.onSuccess(Status.CATEGORY_EDIT_SUCCESS, null);
        }catch (UserException.UserNonExistsException ex){
            throw new UserException.UserNonExistsException("등록되지 않은 사용자입니다.");
        }

    }



}
