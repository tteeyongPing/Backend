package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscribePlaylist is a Querydsl query type for SubscribePlaylist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscribePlaylist extends EntityPathBase<SubscribePlaylist> {

    private static final long serialVersionUID = 346407861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscribePlaylist subscribePlaylist = new QSubscribePlaylist("subscribePlaylist");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPlaylist playlist;

    public final QUser user;

    public QSubscribePlaylist(String variable) {
        this(SubscribePlaylist.class, forVariable(variable), INITS);
    }

    public QSubscribePlaylist(Path<? extends SubscribePlaylist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscribePlaylist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscribePlaylist(PathMetadata metadata, PathInits inits) {
        this(SubscribePlaylist.class, metadata, inits);
    }

    public QSubscribePlaylist(Class<? extends SubscribePlaylist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.playlist = inits.isInitialized("playlist") ? new QPlaylist(forProperty("playlist"), inits.get("playlist")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

