package dgu.cse.newsee.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = true, length = 256)
    private String description;

    @Column(nullable = false)
    private int subscribers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlaylistNews> playlistNews;

    @Builder
    public Playlist(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.subscribers = 0;
    }

    public void setName(String playlistName) {
        this.name = playlistName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void incrementSubscribers() {
        this.subscribers++;
    }

    public void decrementSubscribers() {
        if (this.subscribers > 0) { this.subscribers--; }
    }
}
