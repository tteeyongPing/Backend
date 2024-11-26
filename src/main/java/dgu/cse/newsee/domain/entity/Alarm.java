package dgu.cse.newsee.domain.entity;

import dgu.cse.newsee.domain.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
@Table(name = "Alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 256)
    private String period;

    @Column(nullable = false)
    private boolean active;



    @Convert(converter = StringListConverter.class)
    @Column(nullable = false, columnDefinition = "JSON") // MySQL JSON 타입 사용
    private List<String> days; // 요일 문자열 리스트

    public Alarm( Long userId, String period, boolean active, List<String> day) {

        this.userId = userId;
        this.period = period;
        this.active = active;
        this.days = day;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public List<String> getDay() {
        return days;
    }

    public void setDay(List<String> day) {
        this.days = day;
    }
}
