package dgu.cse.newsee.domain.enums;

import dgu.cse.newsee.apiPayload.exception.NewsException;

public enum Category {
    경제("1", "business"),
    연예오락("2", "entertainment"),
    사회("3", "general"),
    건강의료("4", "health"),
    과학기술("5", "science"),
    스포츠("6", "sports"),
    문화예술("7", "technology");

    private final String id;
    private final String english;

    Category(String id, String english) {
        this.id = id;
        this.english = english;
    }

    public String getId() {
        return id;
    }

    public String getEnglish() {
        return english;
    }

    // ID로 카테고리 이름 반환
    public static Category fromId(String id) {
        for (Category category : values()) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 ID 입니다.");
    }


    // 한글 이름으로 카테고리 반환
    public static Category fromName(String name) {
        for (Category category : values()) {
            if (category.name().equals(name)) {
                return category;
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 이름입니다.");
    }

    // 영어 이름으로 한글 반환
    public static String getKoreanByEnglish(String english) {
        for (Category category : Category.values()) {
            if (category.english.equalsIgnoreCase(english)) {
                return category.name(); // 한글 카테고리 이름 반환
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 영어 이름입니다.");
    }

    // 영어 이름으로 카테고리 반환
    public static Category fromEnglish(String english) {
        for (Category category : Category.values()) {
            if (category.english.equalsIgnoreCase(english)) {
                return category;
            }
        }
        throw new NewsException.CategoryNonExistsException("존재하지 않는 카테고리 영어 이름입니다.");
    }

}
