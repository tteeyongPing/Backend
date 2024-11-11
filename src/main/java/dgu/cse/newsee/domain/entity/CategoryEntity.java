package dgu.cse.newsee.domain.entity;

import dgu.cse.newsee.domain.enums.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category name;  // Enum 타입으로 필드 정의

    // 기본 생성자
    public CategoryEntity() {}

    // 생성자
    public CategoryEntity(Category name) {
        this.name = name;
    }

    // getter와 setter
    public Long getId() {
        return id;
    }

    public Category getName() {
        return name;
    }

    public void setName(Category name) {
        this.name = name;
    }
}
