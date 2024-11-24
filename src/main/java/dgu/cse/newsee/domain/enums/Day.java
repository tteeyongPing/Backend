package dgu.cse.newsee.domain.enums;

public enum Day {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String displayName;

    Day(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Day fromDisplayName(String displayName) {
        for (Day day : Day.values()) {
            if (day.getDisplayName().equals(displayName)) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid display name: " + displayName);
    }
}
