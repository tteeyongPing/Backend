package dgu.cse.newsee.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String category;

    @Column(nullable = false, length = 256)
    private String title;

    @Column(nullable = false, length = 256)
    private String date;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 256)
    private String company;

    @Column(nullable = true)
    private String shorts;

    @Column(nullable = false, length = 256)
    private String reporter;

    @Column(nullable = false)
    private String link;

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }
}
