package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaylistNews is a Querydsl query type for PlaylistNews
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaylistNews extends EntityPathBase<PlaylistNews> {

    private static final long serialVersionUID = -45353556L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaylistNews playlistNews = new QPlaylistNews("playlistNews");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QNews news;

    public final QPlaylist playlist;

    public QPlaylistNews(String variable) {
        this(PlaylistNews.class, forVariable(variable), INITS);
    }

    public QPlaylistNews(Path<? extends PlaylistNews> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaylistNews(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaylistNews(PathMetadata metadata, PathInits inits) {
        this(PlaylistNews.class, metadata, inits);
    }

    public QPlaylistNews(Class<? extends PlaylistNews> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news")) : null;
        this.playlist = inits.isInitialized("playlist") ? new QPlaylist(forProperty("playlist"), inits.get("playlist")) : null;
    }

}

