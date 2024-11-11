package dgu.cse.newsee.domain.enums;

public enum Category {
    정치(1),
    경제(2),
    사회(3),
    국제(4),
    스포츠(5),
    문화예술(6),
    과학기술(7),
    건강의료(8),
    연예오락(9),
    환경(10);

    private final int id;

    Category(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Category fromId(int id) {
        for (Category category : values()) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Category ID: " + id);
    }


}
