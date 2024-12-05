package dgu.cse.newsee.domain.entity;

import dgu.cse.newsee.domain.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "Alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 256)
    private String period;

    @Column(nullable = false)
    private boolean active;

    @Convert(converter = StringListConverter.class)
    @Column(nullable = false, columnDefinition = "JSON")
    private List<String> days;

    public void setId(Long id) {
        this.id = id;
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

    public void setDay(List<String> day) {
        this.days = day;
    }
}
