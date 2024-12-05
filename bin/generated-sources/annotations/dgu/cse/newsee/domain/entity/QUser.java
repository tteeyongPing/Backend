package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -722177870L;

    public static final QUser user = new QUser("user");

    public final ListPath<Bookmark, QBookmark> bookmarks = this.<Bookmark, QBookmark>createList("bookmarks", Bookmark.class, QBookmark.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<NewsMemo, QNewsMemo> newsMemos = this.<NewsMemo, QNewsMemo>createList("newsMemos", NewsMemo.class, QNewsMemo.class, PathInits.DIRECT2);

    public final ListPath<Playlist, QPlaylist> playlistList = this.<Playlist, QPlaylist>createList("playlistList", Playlist.class, QPlaylist.class, PathInits.DIRECT2);

    public final ListPath<SubscribePlaylist, QSubscribePlaylist> subscribePlaylistList = this.<SubscribePlaylist, QSubscribePlaylist>createList("subscribePlaylistList", SubscribePlaylist.class, QSubscribePlaylist.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

