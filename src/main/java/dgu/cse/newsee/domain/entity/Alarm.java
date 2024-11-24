package dgu.cse.newsee.domain.entity;

import dgu.cse.newsee.domain.enums.Day;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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



    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Day day;

    public Alarm(Long userId, String period, boolean active, Day day) {
        this.userId = userId;
        this.period = period;
        this.active = active;
        this.day = day;
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
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
