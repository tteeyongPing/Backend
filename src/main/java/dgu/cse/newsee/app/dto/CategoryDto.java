package dgu.cse.newsee.app.dto;


import dgu.cse.newsee.domain.enums.Category;

public class CategoryDto {
    private int categoryId;
    private String categoryName;

    public CategoryDto(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.name();
    }

    // Getter
    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
