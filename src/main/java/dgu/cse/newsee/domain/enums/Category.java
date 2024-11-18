package dgu.cse.newsee.domain.enums;
import dgu.cse.newsee.apiPayload.exception.NewsException;

public enum Category {
    정치("1"),
    경제("2"),
    사회("3"),
    국제("4"),
    스포츠("5"),
    문화예술("6"),
    과학기술("7"),
    건강의료("8"),
    연예오락("9"),
    환경("10");

    private final String id;

    Category(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Category fromId(String id) {
        for (Category category : values()) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 ID 입니다.");
    }


    public static Category fromName(String name) {
        for (Category category : values()) {
            if (category.name().equals(name)) {
                return category;
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 이름입니다.");
    }
}
