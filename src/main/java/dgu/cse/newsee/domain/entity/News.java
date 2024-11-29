package dgu.cse.newsee.domain.entity;

import dgu.cse.newsee.domain.enums.Category;
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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 256)
    private String company;

    @Column(nullable = true)
    private String shorts;

    @Column(nullable = false, length = 256)
    private String reporter;

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }
}
