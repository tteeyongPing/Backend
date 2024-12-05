package dgu.cse.newsee.app.dto;
import lombok.Getter;
import lombok.Setter;

import dgu.cse.newsee.domain.enums.Category;
@Getter
@Setter
public class CategoryDto {
    private int id;
    private String categoryName;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.categoryName = category.name();
    }


}
