package dgu.cse.newsee.app.dto;


import dgu.cse.newsee.domain.enums.Category;

public class CategoryDto {
    private String id;
    private String categoryName;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.name();
    }

    // Getter
    public String getCategoryId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
